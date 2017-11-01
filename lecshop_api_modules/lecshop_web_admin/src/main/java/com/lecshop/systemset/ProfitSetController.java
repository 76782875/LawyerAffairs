package com.lecshop.systemset;

import com.lecshop.admin.profitset.bean.ProfitSet;
import com.lecshop.admin.profitset.service.ProfitSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 律师收益设置控制器
 *
 * @author sunluyang on 2017/7/11.
 */
@Controller
public class ProfitSetController {

    @Autowired
    private ProfitSetService profitSetService;

    /**
     * 跳转到律师收益设置页面
     *
     * @return 师收益设置页面
     */
    @RequestMapping("/toprofitset")
    public ModelAndView toProfitSet() {
        return new ModelAndView("systemset/profitset");
    }

    /**
     * 查询律师收益设置
     *
     * @return 律师收益设置信息
     */
    @RequestMapping("/queryprofitset")
    @ResponseBody
    public ProfitSet queryProfitSet() {
        return profitSetService.queryProfitSet();
    }

    /**
     * 编辑律师收益设置
     *
     * @param type      类型
     * @param profitSet 律师收益设置实体类
     * @return 编辑返回码
     */
    @RequestMapping("/editprofitset")
    @ResponseBody
    public int editProfitSet(int type, @RequestBody ProfitSet profitSet) {
        return profitSetService.editProfitSet(type, profitSet);
    }
}
