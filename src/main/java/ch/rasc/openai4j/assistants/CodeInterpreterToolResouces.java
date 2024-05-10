package ch.rasc.openai4j.assistants;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@SuppressWarnings({ "hiding" })
public class CodeInterpreterToolResouces implements ToolResources {

	@JsonProperty("file_ids")
	private final List<String> fileIds;

	private CodeInterpreterToolResouces(Builder builder) {
		this.fileIds = builder.fileIds;
	}

	public List<String> getFileIds() {
		return this.fileIds;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private List<String> fileIds = new ArrayList<>();

		/**
		 * A list of file IDs made available to the code_interpreter tool. There can be a
		 * maximum of 20 files associated with the tool.
		 */
		public Builder fileIds(List<String> fileIds) {
			this.fileIds = new ArrayList<>(fileIds);
			return this;
		}

		/**
		 * A list of file IDs made available to the code_interpreter tool. There can be a
		 * maximum of 20 files associated with the tool.
		 */
		public Builder addFileIds(String... fileIds) {
			if (fileIds == null || fileIds.length == 0) {
				return this;
			}

			if (this.fileIds == null) {
				this.fileIds = new ArrayList<>();
			}
			this.fileIds.addAll(List.of(fileIds));
			return this;
		}

		public CodeInterpreterToolResouces build() {
			return new CodeInterpreterToolResouces(this);
		}
	}
}
