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
package ch.rasc.openai4j.batch;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@SuppressWarnings({ "unused", "hiding" })
public class BatchCreateRequest {

	@JsonProperty("input_file_id")
	private final String inputFileId;
	private final String endpoint;
	@JsonProperty("completion_window")
	private final String completionWindow;
	private final Object metadata;

	private BatchCreateRequest(Builder builder) {
		if (builder.inputFileId == null || builder.inputFileId.isEmpty()) {
			throw new IllegalArgumentException("inputFileId must not be null or empty");
		}
		this.inputFileId = builder.inputFileId;

		if (builder.endpoint == null || builder.endpoint.isEmpty()) {
			this.endpoint = "/v1/chat/completions";
		}
		else {
			this.endpoint = builder.endpoint;
		}

		if (builder.completionWindow == null || builder.completionWindow.isEmpty()) {
			this.completionWindow = "24h";
		}
		else {
			this.completionWindow = builder.completionWindow;
		}

		this.metadata = builder.metadata;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String inputFileId;
		private String endpoint;
		private String completionWindow;
		private Object metadata;

		/**
		 * The ID of an uploaded file that contains requests for the new batch.
		 * <p>
		 * Your input file must be formatted as a JSONL file, and must be uploaded with
		 * the purpose <code>batch</code>.
		 */
		public Builder inputFileId(String inputFileId) {
			this.inputFileId = inputFileId;
			return this;
		}

		/**
		 * The endpoint to be used for all requests in the batch. Currently only
		 * /v1/chat/completions is supported.
		 */
		public Builder endpoint(String endpoint) {
			this.endpoint = endpoint;
			return this;
		}

		/**
		 * The time frame within which the batch should be processed. Currently only 24h
		 * is supported.
		 */
		public Builder completionWindow(String completionWindow) {
			this.completionWindow = completionWindow;
			return this;
		}

		/**
		 * Optional custom metadata for the batch.
		 */
		public Builder metadata(Object metadata) {
			this.metadata = metadata;
			return this;
		}

		public BatchCreateRequest build() {
			return new BatchCreateRequest(this);
		}
	}

}
