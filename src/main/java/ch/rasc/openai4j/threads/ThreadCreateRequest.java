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
package ch.rasc.openai4j.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@SuppressWarnings({ "unused", "hiding" })
public class ThreadCreateRequest {

	private final List<ThreadMessageRequest> messages;
	private final Map<String, Object> metadata;

	private ThreadCreateRequest(Builder builder) {
		this.messages = builder.messages;
		this.metadata = builder.metadata;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private List<ThreadMessageRequest> messages;
		private Map<String, Object> metadata;

		private Builder() {
		}

		/**
		 * A list of messages to start the thread with.
		 */
		public Builder messages(List<ThreadMessageRequest> messages) {
			this.messages = new ArrayList<>(messages);
			return this;
		}

		/**
		 * Adds messages to start the thread with.
		 */
		public Builder addMessages(ThreadMessageRequest... message) {
			if (this.messages == null) {
				this.messages = new ArrayList<>();
			}
			this.messages.addAll(List.of(message));
			return this;
		}

		/**
		 * Adds a message to start the thread with.
		 */
		public Builder addMessage(
				Function<ThreadMessageRequest.Builder, ThreadMessageRequest.Builder> fn) {
			return addMessages(fn.apply(ThreadMessageRequest.builder()).build());
		}

		/**
		 * Set of 16 key-value pairs that can be attached to an object. This can be useful
		 * for storing additional information about the object in a structured format.
		 * Keys can be a maximum of 64 characters long and values can be a maxium of 512
		 * characters long.
		 */
		public Builder metadata(Map<String, Object> metadata) {
			this.metadata = new HashMap<>(metadata);
			return this;
		}

		/**
		 * Add a key-value pair to the metadata
		 */
		public Builder putMetadata(String key, Object value) {
			if (this.metadata == null) {
				this.metadata = new HashMap<>();
			}
			this.metadata.put(key, value);
			return this;
		}

		public ThreadCreateRequest build() {
			return new ThreadCreateRequest(this);
		}
	}
}
