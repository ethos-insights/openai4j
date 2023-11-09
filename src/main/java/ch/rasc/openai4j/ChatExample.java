package ch.rasc.openai4j;

import ch.rasc.openai4j.chatcompletions.ChatCompletionCreateRequest;
import ch.rasc.openai4j.chatcompletions.SystemMessage;
import ch.rasc.openai4j.chatcompletions.UserMessage;

public class ChatExample {
	public static void main(String[] args) {
		String apiKey = Util.getApiKey();

		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		var request = ChatCompletionCreateRequest.builder()
				.addMessage(SystemMessage.of("You are a helpful assistant"))
				.addMessage(UserMessage.of("What is the capital of Spain?"))
				.model("gpt-4-1106-preview").build();

		var response = client.chatCompletions.create(request);
		System.out.println(response);
	}

}
