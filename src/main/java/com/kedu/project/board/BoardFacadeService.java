package com.kedu.project.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.project.comment.CommentService;
import com.kedu.project.file_info.FileService;

@Service
@Transactional
public class BoardFacadeService {
	@Autowired
	private BoardService bServ;
	@Autowired
	private FileService fServ;
	@Autowired
	private CommentService cServ;

	//보드 작성
	public int postBoard(BoardDTO dto, MultipartFile[] files ) {
		int targetSeq = bServ.postBoard(dto);//발행한 시퀀스 번호 가져와야함
		System.out.println("파케이드레이어:"+targetSeq);
		fServ.insert(files,"board",targetSeq,dto.getUser_id()); //파일데이터, 타겟 타입, 타겟 시퀀스, 유저 아이디
		return targetSeq;
	}
	
	//보드 삭제
	public int deleteBoard(int board_seq, String user_id, String target_type) {
		//1. 보드에 딸린 첨부파일 삭제(db+gcs다)+2.보드에 딸린 이미지 파일 삭제(db+gcs다)
		fServ.deleteAllFile(board_seq, user_id, target_type);

		//2.보드에 딸린 댓글 전체 삭제
		cServ.deleteAllComment(board_seq);
		
		//3.보드 삭제
		return bServ.deleteTargetBoard(board_seq, user_id);
	}
	
	
	
}
