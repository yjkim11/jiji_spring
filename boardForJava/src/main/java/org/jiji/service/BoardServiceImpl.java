package org.jiji.service;

import java.util.List;

import org.jiji.domain.BoardAttachVO;
import org.jiji.domain.BoardVO;
import org.jiji.domain.Criteria;
import org.jiji.mapper.BoardAttachMapper;
import org.jiji.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

	private BoardMapper mapper;
	
	private BoardAttachMapper attachMapper;

	@Transactional
	@Override
	public void register(BoardVO board) {

		mapper.insertSelectKey(board);
		
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
	}

	@Override
	public BoardVO get(Long bno) {
		// TODO Auto-generated method stub
		return mapper.read(bno);
	}

	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		
		log.info("modify.........." + board);
		
		attachMapper.deleteAll(board.getBno());
		
		boolean modifyResult = mapper.update(board) == 1;
		
		if(modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
			
			board.getAttachList().forEach(attach -> {
				
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
		}
		
		return modifyResult;
	}

	@Transactional
	@Override
	public int remove(Long bno) {
		
		log.info("remove....." + bno);
		
		attachMapper.deleteAll(bno);
		
		return mapper.delete(bno);
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {

		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {

		return mapper.getTotalCount(cri);
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {

		log.info("get Attach list by bno: " + bno);
		
		return attachMapper.findByBno(bno);
	}
	
	
	
	
	

//	@Override
//	public List<BoardVO> getList() {
//		// TODO Auto-generated method stub
//		return mapper.getList();
//	}
	
	
	
	
	
	
	
}
