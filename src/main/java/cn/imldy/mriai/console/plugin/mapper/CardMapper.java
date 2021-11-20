package cn.imldy.mriai.console.plugin.mapper;

import cn.imldy.mriai.console.plugin.bean.Card;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author imldy
 * @date 2021/11/20 9:43
 **/
@Mapper
public interface CardMapper {
    boolean addCard(Card card);
    boolean updateCard(Card card);
    Card getCardByNo(String no);
    boolean isExistByNo(String no);
}
