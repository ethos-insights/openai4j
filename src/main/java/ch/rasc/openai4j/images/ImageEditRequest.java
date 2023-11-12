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

import java.nio.file.Path;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonValue;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE, depluralize = true)
public interface ImageEditRequest {

	static Builder builder() {
		return new Builder();
	}

	/**
	 * The image to edit. Must be a valid PNG file, less than 4MB, and square. If mask is
	 * not provided, image must have transparency, which will be used as the mask.
	 */
	Path image();

	/**
	 * A text description of the desired image(s). The maximum length is 1000 characters.
	 */
	String prompt();

	/**
	 * An additional image whose fully transparent areas (e.g. where alpha is zero)
	 * indicate where image should be edited. Must be a valid PNG file, less than 4MB, and
	 * have the same dimensions as image.
	 */
	@Nullable
	Path mask();

	/**
	 * The model to use for image generation. Only dall-e-2 is supported at this time.
	 * Defaults to dall-e-2.
	 */
	@Nullable
	Model model();

	/**
	 * The number of images to generate. Must be between 1 and 10. Defaults to 1.
	 */
	@Nullable
	Integer n();

	/**
	 * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
	 * Defaults to 1024x1024.
	 */
	@Nullable
	Size size();

	/**
	 * The format in which the generated images are returned. Must be one of url or
	 * b64_json. Defaults to url.
	 */
	@Nullable
	ResponseFormat responseFormat();

	/**
	 * A unique identifier representing your end-user, which can help OpenAI to monitor
	 * and detect abuse.
	 */
	@Nullable
	String user();

	enum Model {
		DALL_E_2("dall-e-2");

		private final String value;

		Model(String value) {
			this.value = value;
		}

		@JsonValue
		String toValue() {
			return this.value;
		}
	}

	enum ResponseFormat {
		URL("url"), B64_JSON("b64_json");

		private final String value;

		ResponseFormat(String value) {
			this.value = value;
		}

		@JsonValue
		String toValue() {
			return this.value;
		}
	}

	enum Size {
		S_256("256x256"), S_512("512x512"), S_1024("1024x1024");

		private final String value;

		Size(String value) {
			this.value = value;
		}

		@JsonValue
		String toValue() {
			return this.value;
		}
	}

	final class Builder extends ImmutableImageEditRequest.Builder {
	}
}
