package org.jeecg.modules.controller.biz;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.biz.entity.BizOppt;
import org.jeecg.modules.biz.service.IBizOpptService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: crm_biz_oppt
 * @Author: jeecg-boot
 * @Date:   2020-09-18
 * @Version: V1.0
 */
@Api(tags="crm_biz_oppt")
@RestController
@RequestMapping("/biz/bizOppt")
@Slf4j
public class BizOpptController extends JeecgController<BizOppt, IBizOpptService> {
	@Autowired
	private IBizOpptService bizOpptService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bizOppt
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "crm_biz_oppt-分页列表查询")
	@ApiOperation(value="crm_biz_oppt-分页列表查询", notes="crm_biz_oppt-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BizOppt bizOppt,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BizOppt> queryWrapper = QueryGenerator.initQueryWrapper(bizOppt, req.getParameterMap());
		Page<BizOppt> page = new Page<BizOppt>(pageNo, pageSize);
		IPage<BizOppt> pageList = bizOpptService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param bizOppt
	 * @return
	 */
	@AutoLog(value = "crm_biz_oppt-添加")
	@ApiOperation(value="crm_biz_oppt-添加", notes="crm_biz_oppt-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BizOppt bizOppt) {
		bizOpptService.save(bizOppt);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param bizOppt
	 * @return
	 */
	@AutoLog(value = "crm_biz_oppt-编辑")
	@ApiOperation(value="crm_biz_oppt-编辑", notes="crm_biz_oppt-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BizOppt bizOppt) {
		bizOpptService.updateById(bizOppt);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "crm_biz_oppt-通过id删除")
	@ApiOperation(value="crm_biz_oppt-通过id删除", notes="crm_biz_oppt-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bizOpptService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "crm_biz_oppt-批量删除")
	@ApiOperation(value="crm_biz_oppt-批量删除", notes="crm_biz_oppt-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bizOpptService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "crm_biz_oppt-通过id查询")
	@ApiOperation(value="crm_biz_oppt-通过id查询", notes="crm_biz_oppt-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BizOppt bizOppt = bizOpptService.getById(id);
		if(bizOppt==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(bizOppt);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bizOppt
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BizOppt bizOppt) {
        return super.exportXls(request, bizOppt, BizOppt.class, "crm_biz_oppt");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BizOppt.class);
    }

}
