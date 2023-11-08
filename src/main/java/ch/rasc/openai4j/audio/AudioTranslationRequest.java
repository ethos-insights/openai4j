package ch.rasc.openai4j.audio;

import java.nio.file.Path;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public interface AudioTranslationRequest {

	enum ResponseFormat {
		JSON("json"), TEXT("text"), SRT("srt"), VERBOSE_JSON("verbose_json"), VTT("vtt");

		private final String value;

		ResponseFormat(String value) {
			this.value = value;
		}

		@JsonValue
		public String toValue() {
			return this.value;
		}
	}

	enum Model {
		WHISPER_1("whisper-1");

		private final String value;

		Model(String value) {
			this.value = value;
		}

		@JsonValue
		public String toValue() {
			return this.value;
		}
	}

	/**
	 * The audio file object to translate, in one of these formats: flac, mp3, mp4, mpeg,
	 * mpga, m4a, ogg, wav, or webm.
	 */
	Path file();

	/**
	 * ID of the model to use. Only whisper-1 is currently available.
	 */
	@Value.Default
	default Model model() {
		return Model.WHISPER_1;
	}

	/**
	 * An optional text to guide the model's style or continue a previous audio segment.
	 * The prompt should be in English.
	 */
	@Nullable
	String prompt();

	/**
	 * The format of the transcript output. Defaults to json.
	 */
	@JsonProperty("response_format")
	@Nullable
	ResponseFormat responseFormat();

	/**
	 * The sampling temperature, between 0 and 1. Higher values like 0.8 will make the
	 * output more random, while lower values like 0.2 will make it more focused and
	 * deterministic. If set to 0, the model will use log probability to automatically
	 * increase the temperature until certain thresholds are hit.
	 *
	 * Defaults to 0.
	 */
	@Nullable
	Double temperature();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableAudioTranslationRequest.Builder {
	}
}
