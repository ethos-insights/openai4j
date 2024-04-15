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
package ch.rasc.openai4j.finetuningjobs;

import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@SuppressWarnings({ "unused", "hiding" })
public class FineTuningJobCreateRequest {
	private final String model;
	@JsonProperty("training_file")
	private final String trainingFile;
	private final Hyperparameters hyperparameters;
	private final String suffix;
	@JsonProperty("validation_file")
	private final String validationFile;
	private final Integer seed;

	private FineTuningJobCreateRequest(Builder builder) {
		if (builder.model == null || builder.model.isBlank()) {
			throw new IllegalArgumentException("model cannot be null");
		}
		if (builder.trainingFile == null || builder.trainingFile.isBlank()) {
			throw new IllegalArgumentException("trainingFile cannot be null");
		}
		this.model = builder.model;
		this.trainingFile = builder.trainingFile;
		this.hyperparameters = builder.hyperparameters;
		this.suffix = builder.suffix;
		this.validationFile = builder.validationFile;
		this.seed = builder.seed;
	}

	@JsonInclude(Include.NON_EMPTY)
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public static class Hyperparameters {
		@JsonProperty("batch_size")
		private final Object batchSize;
		@JsonProperty("learning_rate_multiplier")
		private final Object learningRateMultiplier;
		@JsonProperty("n_epochs")
		private final Object nEpochs;

		private Hyperparameters(Builder builder) {
			this.batchSize = builder.batchSize;
			this.learningRateMultiplier = builder.learningRateMultiplier;
			this.nEpochs = builder.nEpochs;
		}

		public static Builder builder() {
			return new Builder();
		}

		public static final class Builder {
			private Object batchSize;
			private Object learningRateMultiplier;
			private Object nEpochs;

			private Builder() {
			}

			/**
			 * Number of examples in each batch. A larger batch size means that model
			 * parameters are updated less frequently, but with lower variance. Defaults
			 * to auto
			 */
			public Builder batchSize(int batchSize) {
				this.batchSize = batchSize;
				return this;
			}

			/**
			 * Sets batch size to "auto"
			 */
			public Builder batchSizeAuto() {
				this.batchSize = "auto";
				return this;
			}

			/**
			 * Scaling factor for the learning rate. A smaller learning rate may be useful
			 * to avoid overfitting. Defaults to auto
			 */
			public Builder learningRateMultiplier(double learningRateMultiplier) {
				this.learningRateMultiplier = learningRateMultiplier;
				return this;
			}

			/**
			 * Sets scaling factor for the learning rate "auto"
			 */
			public Builder learningRateMultiplierAuto() {
				this.learningRateMultiplier = "auto";
				return this;
			}

			/**
			 * The number of epochs to train the model for. An epoch refers to one full
			 * cycle through the training dataset. Defaults to auto
			 */
			public Builder nEpochs(int nEpochs) {
				this.nEpochs = nEpochs;
				return this;
			}

			/**
			 * Sets number of epochs to "auto"
			 */
			public Builder nEpochsAuto() {
				this.nEpochs = "auto";
				return this;
			}

			public Hyperparameters build() {
				return new Hyperparameters(this);
			}
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String model;
		private String trainingFile;
		private Hyperparameters hyperparameters;
		private String suffix;
		private String validationFile;
		private Integer seed;

		private Builder() {
		}

		/**
		 * The name of the model to fine-tune. You can select one of the supported models.
		 */
		public Builder model(String model) {
			this.model = model;
			return this;
		}

		/**
		 * The ID of an uploaded file that contains training data. Your dataset must be
		 * formatted as a JSONL file. Additionally, you must upload your file with the
		 * purpose fine-tune.
		 */
		public Builder trainingFile(String trainingFile) {
			this.trainingFile = trainingFile;
			return this;
		}

		/**
		 * The hyperparameters used for the fine-tuning job.
		 */
		public Builder hyperparameters(Hyperparameters hyperparameters) {
			this.hyperparameters = hyperparameters;
			return this;
		}

		/**
		 * The hyperparameters used for the fine-tuning job.
		 */
		public Builder hyperparameters(
				Function<Hyperparameters.Builder, Hyperparameters.Builder> fn) {
			this.hyperparameters = fn.apply(Hyperparameters.builder()).build();
			return this;
		}

		/**
		 * A string of up to 18 characters that will be added to your fine-tuned model
		 * name. For example, a suffix of "custom-model-name" would produce a model name
		 * like ft:gpt-3.5-turbo:openai:custom-model-name:7p4lURel.
		 */
		public Builder suffix(String suffix) {
			this.suffix = suffix;
			return this;
		}

		/**
		 * The ID of an uploaded file that contains validation data. If you provide this
		 * file, the data is used to generate validation metrics periodically during
		 * fine-tuning. These metrics can be viewed in the fine-tuning results file. The
		 * same data should not be present in both train and validation files. Your
		 * dataset must be formatted as a JSONL file. You must upload your file with the
		 * purpose fine-tune.
		 */
		public Builder validationFile(String validationFile) {
			this.validationFile = validationFile;
			return this;
		}

		/**
		 * The seed controls the reproducibility of the job. Passing in the same seed and
		 * job parameters should produce the same results, but may differ in rare cases.
		 * If a seed is not specified, one will be generated for you.
		 */
		public Builder seed(Integer seed) {
			this.seed = seed;
			return this;
		}

		public FineTuningJobCreateRequest build() {
			return new FineTuningJobCreateRequest(this);
		}
	}
}
