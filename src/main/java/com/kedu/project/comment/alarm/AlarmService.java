package com.kedu.project.comment.alarm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.project.board.BoardDAO;
import com.kedu.project.comment.CommentDAO;
import com.kedu.project.comment.CommentDTO;

@Service
public class AlarmService {
    @Autowired
    private AlarmDAO dao;

    @Autowired
    private BoardDAO boarddao;

    @Autowired
    private CommentDAO commentdao;

    public String getWriterId(CommentDTO dto) {
        if (dto.getParent_comment_seq() == null) {
            return boarddao.getBoardWriterId(dto.getBoard_seq());
        }else{
            return commentdao.getCommentWriterId(dto.getParent_comment_seq());
        }
    }

    public int saveAlarm(AlarmDTO dto){
        return dao.saveAlarm(dto);
    }

    public List<AlarmDTO> getPendingAlarms(String userId){
        return dao.getPendingAlarms(userId);
    }

    public int deleteAlarm(AlarmDTO dto){
        return dao.deleteAlarm(dto);
    }
}
