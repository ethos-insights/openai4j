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
package ch.rasc.openai4j.images;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE, depluralize = true)
@JsonSerialize(as = ImmutableImageGenerationRequest.class)
@JsonInclude(Include.NON_EMPTY)
public interface ImageGenerationRequest {

	static Builder builder() {
		return new Builder();
	}

	/**
	 * A text description of the desired image(s). The maximum length is 1000 characters
	 * for dall-e-2 and 4000 characters for dall-e-3.
	 */
	String prompt();

	/**
	 * The model to use for image generation. Defaults to dall-e-2
	 */
	@Nullable
	ImageModel model();

	/**
	 * The number of images to generate. Must be between 1 and 10. For dall-e-3, only n=1
	 * is supported. Defaults to 1
	 */
	@Nullable
	Integer n();

	/**
	 * The quality of the image that will be generated. hd creates images with finer
	 * details and greater consistency across the image. This param is only supported for
	 * dall-e-3. Defaults to standard
	 */
	@Nullable
	Quality quality();

	/**
	 * The format in which the generated images are returned. Must be one of url or
	 * b64_json. Defaults to url
	 */
	@JsonProperty("response_format")
	@Nullable
	ImageResponseFormat responseFormat();

	/**
	 * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024 for
	 * dall-e-2. Must be one of 1024x1024, 1792x1024, or 1024x1792 for dall-e-3 models.
	 * Defaults to 1024x1024
	 */
	@Nullable
	ImageSize size();

	/**
	 * The style of the generated images. Must be one of vivid or natural. Vivid causes
	 * the model to lean towards generating hyperreal and dramatic images. Natural causes
	 * the model to produce more natural, less hyperreal looking images. This param is
	 * only supported for dall-e-3. Defaults to vivid
	 */
	@Nullable
	Style style();

	/**
	 * A unique identifier representing your end-user, which can help OpenAI to monitor
	 * and detect abuse.
	 */
	@Nullable
	String user();

	enum Quality {
		STANDARD("standard"), HD("hd");

		private final String value;

		Quality(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	enum Style {
		VIVID("vivid"), NATURAL("natural");

		private final String value;

		Style(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	final class Builder extends ImmutableImageGenerationRequest.Builder {
	}

}
