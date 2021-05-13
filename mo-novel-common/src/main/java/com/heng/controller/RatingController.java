package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.Rating;
import com.heng.entity.User;
import com.heng.service.RatingService;
import com.heng.vo.RatingQueryVo;
import com.heng.vo.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 小说五星评分 前端控制器
 * </p>
 *
 * @author LJohn
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping("/search")
    public ResponseDTO searchRating(RatingQueryVo ratingQueryVo)
    {
        List<Rating> ratings = ratingService.searchRating(ratingQueryVo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", ratings);
        map.put("total", ratings.size());
        return ResponseDTO.succ(map);
    }

    @PostMapping("add")
    public ResponseDTO addRating(@Validated @RequestBody Rating rating)
    {
        ratingService.save(rating);
        return ResponseDTO.succ("评分成功");
    }

    @PostMapping("edit")
    public ResponseDTO editRating(@Validated @RequestBody Rating rating)
    {
        ratingService.updateById(rating);
        return ResponseDTO.succ("改分成功");
    }

    @GetMapping("delete/{ratingId}")
    public ResponseDTO deleteRating(@PathVariable Long ratingId)
    {

        ratingService.removeById(ratingId);
        return ResponseDTO.succ(null);
    }
}

