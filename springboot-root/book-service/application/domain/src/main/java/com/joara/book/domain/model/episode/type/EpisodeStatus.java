package com.joara.book.domain.model.episode.type;

public enum EpisodeStatus {
	/** 임시 저장 등 */
	PENDING,
	/** 활성화(Normal) */
	ACTIVE,
	/** Episode가 실제로 삭제 됐을 때 */
	REMOVED,
	/** 상위 Book이 삭제 됐을 때 등 */
	DISABLED,
	/** 블락 */
	BLOCK
}
