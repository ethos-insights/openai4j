/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.rasc.openai4j.chatcompletions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.rasc.openai4j.chatcompletions.ChatCompletionsResponse.Message;
import ch.rasc.openai4j.chatcompletions.ChatCompletionsResponse.ToolCall;

public class AssistantMessage extends ChatCompletionMessage {
	private final String content;

	private final String name;

	private final List<ToolCall> toolCalls;

	private AssistantMessage(String content, String name, List<ToolCall> toolCalls) {
		this.content = content;
		this.name = name;
		if (toolCalls != null) {
			this.toolCalls = List.copyOf(toolCalls);
		}
		else {
			this.toolCalls = null;
		}
	}

	/**
	 * Create a new assistant message
	 *
	 * @param content The contents of the system message.
	 * @param name An optional name for the participant. Provides the model information to
	 * differentiate between participants of the same role.
	 * @param toolCalls The tool calls generated by the model, such as function calls.
	 */
	public static AssistantMessage of(String content, String name,
			List<ToolCall> toolCalls) {
		return new AssistantMessage(content, name, toolCalls);
	}

	/**
	 * Create a new assistant message
	 *
	 * @param content The contents of the system message.
	 */
	public static AssistantMessage of(String content) {
		return new AssistantMessage(content, null, null);
	}

	/**
	 * Create a new assistant message from a response message
	 *
	 * @param message The message from a chat completion response
	 */
	public static AssistantMessage of(Message message) {
		return new AssistantMessage(message.content(), null, message.toolCalls());
	}

	/**
	 * The contents of the assistant message.
	 */
	@JsonProperty
	@JsonInclude
	public String content() {
		return this.content;
	}

	/**
	 * An optional name for the participant. Provides the model information to
	 * differentiate between participants of the same role.
	 */
	@JsonProperty
	public String name() {
		return this.name;
	}

	/**
	 * The tool calls generated by the model, such as function calls.
	 */
	@JsonProperty("tool_calls")
	public List<ToolCall> toolCalls() {
		return this.toolCalls;
	}

	/**
	 * The role of the messages author, in this case <code>assistant</code>.
	 */
	@Override
	String role() {
		return "assistant";
	}
}
