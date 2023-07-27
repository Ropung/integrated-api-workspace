package com.joara.book.domain.model.episode.type;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public enum EpisodeStatus {
	/** 임시 저장 등 */
	PENDING(false),
	/** 활성화(Normal) */
	ACTIVE(true),
	/** Episode가 실제로 삭제 됐을 때 */
	REMOVED(false),
	/** 상위 Book이 삭제 됐을 때 등 */
	DISABLED(false),
	/** 블락 */
	BLOCK(false);

	public final boolean readable;

	public static List<EpisodeStatus> readableItems() {
		List<EpisodeStatus> readableItems = new ArrayList<>();

		for (var status: values()) {
			if (status.readable) {
				readableItems.add(status);
			}
		}

		return readableItems;
	}
}
