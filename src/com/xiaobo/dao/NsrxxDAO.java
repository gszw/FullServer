package com.xiaobo.dao;

import java.util.List;




import com.xiaobo.model.FpCx;
import com.xiaobo.model.Fpfplist;
import com.xiaobo.model.Fplist;
import com.xiaobo.model.NsrDefx;
import com.xiaobo.model.NsrDfsxx;
import com.xiaobo.model.NsrFpfx;
import com.xiaobo.model.NsrFphd;
import com.xiaobo.model.NsrFpjc;
import com.xiaobo.model.NsrHdxx;
import com.xiaobo.model.NsrJbzq;
import com.xiaobo.model.NsrJkxx;
import com.xiaobo.model.NsrJkxxmx;
import com.xiaobo.model.NsrJmxx;
import com.xiaobo.model.NsrList;
import com.xiaobo.model.NsrSb;
import com.xiaobo.model.NsrSbmx;
import com.xiaobo.model.NsrTfyxx;
import com.xiaobo.model.NsrYh;
import com.xiaobo.model.Nsrxx;
import com.xiaobo.model.PubView;
import com.xiaobo.model.Rkfx_jc;
import com.xiaobo.model.SysPzList;
import com.xiaobo.model.main;

public interface NsrxxDAO {
	//获得纳税人总数
	public String getNsrxxTotal(String nsrmc);
	//纳税人基本信息
	public Nsrxx getNsrBasicInfo(String nsrnbm);	
	// 根据纳税人内部码，返回该纳税人银行信息
	public List<NsrYh> getNsrYList(String nsrnbm);
	// 根据某条件，返回某页查询纳税人信息，多条
	public List<NsrList> getMainList(String sqlstr, int PageNumber);
	// 根据某条件，返回某页查询纳税人总条数
	public List<String> getMainCount(String sqlstr) ;	
	//根据纳税人内部码，返回该纳税人核定信息
	public List<NsrHdxx> getNsrHdxxList(String nsrnbm);
	//根据纳税人内部码，返回该纳税人  地方税核定信息
	public List<NsrDfsxx> getNsrDfsxxList(String nsrnbm);
	//查询纳税人停复业信息
	public List<NsrTfyxx> getNsrTfyxx(String nsrnbm);
	// 根据手机号返回税务人员编码，如果为-１则表示该手机号不存在
	public String getSjNum(String sj);
	// 根据某纳税人内部码，返回该纳税人本年度申报情况总表数据
	public List<NsrSb> getSbList(String nsrnbm);
	// 根据某纳税人内部码,申报日期，返回该纳税人申报明细信息
	public List<NsrSbmx> getSbmxList(String nsrnbm,String sb_rq);
	// 根据某纳税人内部码，返回该纳税人缴款信息
	public List<NsrJkxx> getJkxxList(String nsrnbm);
	// 根据某纳税人内部码，返回该纳税人   缴款明细信息
	public List<NsrJkxxmx> getJkxxmxList(String nsrnbm,String jk_rq);	
	// 根据某纳税人内部码，返回该纳税人   发票核定信息
	public List<NsrFphd> getFphdxxList(String nsrnbm);
	// 根据某纳税人内部码，返回该纳税人   发票结存信息
	public List<NsrFpjc> getFpjcxxList(String nsrnbm);
	// 根据某纳税人内部码，返回该纳税人   减免信息
	public List<NsrJmxx> getNsrJmxxList(String nsrnbm);
	// 根据某纳税人内部码，返回该纳税人   减并征期信息
	public List<NsrJbzq> getNsrJbzqList(String nsrnbm);
	// 发票分析
	public List<NsrFpfx> getFpfxList(String nsrnbm);
	// 定额分析
	public List<NsrDefx> getDefxList(String nsrnbm);
	//判断一个人员是不是专管员，如果是返回该人员代码，否则返回空
	public String getIfZgy(String zgydm); 
	//取得某人员所在核算机关的某票证号码所对就的所有票证
	public List<SysPzList> GetPzList(String pzhm,String swry_dm);	
	//取得主页面信息
	public main GetMainList(String swry_dm,String qttj); 	
	//根据不同的TableId返回不同的查询结果
	public List<PubView> GetPublicList(String swry_dm,String TableId,String qttj);	
	// 根据发票号码和发票代码段，返回该票的领用存信息
	public List<Fplist> getFpList(String fphm,String fpdm);
	// 根据发票号码和发票代码段，返回开票明细信息
	public List<Fpfplist> getFpKpList(String fphm,String fpdm);
	// 根据税务人员编码返回单位人员所在机构的分级次入库信息
	public List<Rkfx_jc> getRk_jc(String swry);
	// 根据发票号码和发票代码段，返回查询结果，这是最新查询
	public FpCx getFpCxList(String fphm,String fpdm,String num);

}
