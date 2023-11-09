package ch.rasc.openai4j.moderations;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableModerationsRequest.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface ModerationsRequest {

	enum Model {
		TEXT_MODERATION_STABLE("text-moderation-stable"),
		TEXT_MODERATION_LATEST("text-moderation-latest");

		private final String value;

		Model(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	/**
	 * The input text to classify
	 */
	String input();

	/**
	 * Two content moderations models are available: text-moderation-stable and
	 * text-moderation-latest.
	 * <p>
	 * The default is text-moderation-latest which will be automatically upgraded over
	 * time. This ensures you are always using our most accurate model. If you use
	 * text-moderation-stable, we will provide advanced notice before updating the model.
	 * Accuracy of text-moderation-stable may be slightly lower than for
	 * text-moderation-latest
	 */
	@Nullable
	Model model();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableModerationsRequest.Builder {
	}
}
