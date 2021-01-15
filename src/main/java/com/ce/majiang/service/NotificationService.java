package com.ce.majiang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ce.majiang.dto.NotificationDTO;
import com.ce.majiang.dto.PaginationDTO;
import com.ce.majiang.enums.NotificationStatusEnum;
import com.ce.majiang.enums.NotificationTypeEnum;
import com.ce.majiang.mapper.NotificationMapper;
import com.ce.majiang.model.Notification;
import com.ce.majiang.model.User;
import com.ce.majiang.result.ResultStatus;
import com.ce.majiang.result.exception.CustomizeException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author c__e
 * @date 2021/1/15 11:48
 */
@Service
public class NotificationService extends ServiceImpl<NotificationMapper, Notification> {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO<NotificationDTO> list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        // 总记录条数
        Integer totalCount = notificationMapper.selectCount(new QueryWrapper<Notification>().eq("receiver", userId));
        paginationDTO.setTotalCount(totalCount);
        // 总页数
        int totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        paginationDTO.setTotalPage(totalPage);
        // 页码越界处理
        if (page > totalPage) {
            page = totalPage;
        }
        if (page < 1) {
            page = 1;
        }
        paginationDTO.setPage(page);
        // 查询数据，封装结果
        Integer offset = size * (page - 1);
        List<Notification> notifications = notificationMapper.pageListByUserId(userId, offset, size);
        List<NotificationDTO> list = toNotificationDTOList(notifications);
        // 设置数据
        paginationDTO.setData(list);
        // 设置 首页 上一页 页码 下一页 末页
        paginationDTO.setPagination(page, size);
        return paginationDTO;
    }

    private List<NotificationDTO> toNotificationDTOList(List<Notification> notifications) {
        List<NotificationDTO> list = new ArrayList<>(notifications.size());
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            list.add(notificationDTO);
        }
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectById(id);
        if (ObjectUtils.isEmpty(notification)) {
            throw new CustomizeException(ResultStatus.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            throw new CustomizeException(ResultStatus.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateById(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
