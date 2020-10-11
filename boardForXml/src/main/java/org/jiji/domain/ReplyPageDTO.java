package org.jiji.domain;

import java.util.List;

import javax.annotation.Generated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {

	private int replyCnt;
	private List<ReplyVO> list;
}

