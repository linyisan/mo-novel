package com.heng.controller;


import com.heng.common.ResponseDTO;
import com.heng.entity.Rating;
import com.heng.entity.User;
import com.heng.service.RatingService;
import com.heng.valid.AddGroup;
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
    public ResponseDTO addRating(@Validated(AddGroup.class) @RequestBody Rating rating)
    {
        return ratingService.addRating(rating);
    }

    @PostMapping("edit")
    public ResponseDTO editRating(@Validated @RequestBody Rating rating)
    {
        return ratingService.editRating(rating);
    }

    @GetMapping("delete/{ratingId}")
    public ResponseDTO deleteRating(@PathVariable Long ratingId)
    {
        return ratingService.deleteRating(ratingId);
    }
}

