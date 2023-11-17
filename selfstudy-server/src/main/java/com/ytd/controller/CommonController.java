package com.ytd.controller;

import com.ytd.constant.MessageConstant;
import com.ytd.dto.SeatSelectByPageDTO;
import com.ytd.entity.Notice;
import com.ytd.entity.User;
import com.ytd.result.PageResult;
import com.ytd.result.Result;
import com.ytd.service.CommonService;
import com.ytd.utils.GithubUploaderUtil;
import com.ytd.vo.NoticesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private GithubUploaderUtil githubUploaderUtil;
    @Autowired
    private CommonService commonService;

    /**
     * 文件上传
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String msg = this.githubUploaderUtil.upload(multipartFile);
        if (msg != null) {
            log.info("文件上传至：{}", msg);
            return Result.success(msg);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

    @GetMapping("/selectSeatByArea")
    @ApiOperation("根据区域查询座位信息")
    public Result<PageResult> selectSeatByArea(SeatSelectByPageDTO seatSelectByPageDTO) {
        PageResult pageResult = commonService.selectSeatByArea(seatSelectByPageDTO);
        log.info("{}", pageResult);
        return Result.success(pageResult);
    }

    @GetMapping("/showNotices")
    @ApiOperation("查询所有通知信息")
    public Result<NoticesVO> selectNotices() {
        List<Notice> noticeList = commonService.selectNotices();
        NoticesVO noticesVO = NoticesVO.builder()
                .noticeList(noticeList)
                .build();
        return Result.success(noticesVO);
    }
}
