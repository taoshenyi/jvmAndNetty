package com.unionfin.rocketmq;

import java.math.BigDecimal;
import java.util.List;

public class TaskProcReqDto
{
    /**
     * 
     */
    private static final long serialVersionUID = 6343711552536019325L;

    // 票据受理
    private BigDecimal billProcReqOid;
    // 备用
    private BigDecimal billProcReqOid1;
    // 结算任务
    private BigDecimal settleTaskOid;
    // 结算任务备用
    private BigDecimal settleTaskOid1;

    // 通知任务
    private BigDecimal noticeTaskOid;
    private List<BigDecimal> noticeTaskOidList;
    /** tradeNoticeTask 4 【10/11】任务 **/
    private List<BigDecimal> noticeTaskOidList41011;
    private List<BigDecimal> noticeTaskOidList1;

    // 投资人 代付明细通知任务
    private BigDecimal investorNoticeTaskOid;

    private int batchNo;
    private String batchNoStr;
    private String transClas;
    private List<String> batchNoStrList;

    private BigDecimal batchNumber;

    private String settleDate;
    private String TaskType;
    private BigDecimal slmtId;
    private String settleRng;
    private BigDecimal agentId;
    /**
     * 结算商结算单通知任务主键集合
     */
    private List<BigDecimal> busiNoticeTaskOidList;
    /**
     * 投资人代付的主键oid
     */
    private List<TaskProcReqDto> investorNoticeTaskOidList;
    /** 结算单同步nqsf的任务主键集合 **/
    private List<BigDecimal> noticeTask4SettleList;
    /** 需要同步的结算单数量 **/
    private int task4SettleNotice;
    /** 【31轧差、风险准备金收益凭证同步】 **/
    private BigDecimal taskOidNotice31;
    /** 手续费结算时，如果无风险准备金或轧差，则直接生成40结算单同步任务 **/
    private List<BigDecimal> taskOidNotice40;


    public BigDecimal getBillProcReqOid()
    {
        return billProcReqOid;
    }


    public void setBillProcReqOid(BigDecimal billProcReqOid)
    {
        this.billProcReqOid = billProcReqOid;
    }


    public BigDecimal getSettleTaskOid()
    {
        return settleTaskOid;
    }


    public void setSettleTaskOid(BigDecimal settleTaskOid)
    {
        this.settleTaskOid = settleTaskOid;
    }


    public BigDecimal getNoticeTaskOid()
    {
        return noticeTaskOid;
    }


    public void setNoticeTaskOid(BigDecimal noticeTaskOid)
    {
        this.noticeTaskOid = noticeTaskOid;
    }


    public BigDecimal getInvestorNoticeTaskOid()
    {
        return investorNoticeTaskOid;
    }


    public void setInvestorNoticeTaskOid(BigDecimal investorNoticeTaskOid)
    {
        this.investorNoticeTaskOid = investorNoticeTaskOid;
    }


    public int getBatchNo()
    {
        return batchNo;
    }


    public void setBatchNo(int batchNo)
    {
        this.batchNo = batchNo;
    }


    public BigDecimal getBillProcReqOid1()
    {
        return billProcReqOid1;
    }


    public void setBillProcReqOid1(BigDecimal billProcReqOid1)
    {
        this.billProcReqOid1 = billProcReqOid1;
    }


    public BigDecimal getSettleTaskOid1()
    {
        return settleTaskOid1;
    }


    public void setSettleTaskOid1(BigDecimal settleTaskOid1)
    {
        this.settleTaskOid1 = settleTaskOid1;
    }


    public List<BigDecimal> getNoticeTaskOidList()
    {
        return noticeTaskOidList;
    }


    public void setNoticeTaskOidList(List<BigDecimal> noticeTaskOidList)
    {
        this.noticeTaskOidList = noticeTaskOidList;
    }


    public String getBatchNoStr()
    {
        return batchNoStr;
    }


    public void setBatchNoStr(String batchNoStr)
    {
        this.batchNoStr = batchNoStr;
    }


    public String getTransClas()
    {
        return transClas;
    }


    public void setTransClas(String transClas)
    {
        this.transClas = transClas;
    }


    public List<String> getBatchNoStrList()
    {
        return batchNoStrList;
    }


    public void setBatchNoStrList(List<String> batchNoStrList)
    {
        this.batchNoStrList = batchNoStrList;
    }


    public BigDecimal getBatchNumber()
    {
        return batchNumber;
    }


    public void setBatchNumber(BigDecimal batchNumber)
    {
        this.batchNumber = batchNumber;
    }


    public String getSettleDate()
    {
        return settleDate;
    }


    public void setSettleDate(String settleDate)
    {
        this.settleDate = settleDate;
    }


    public String getTaskType()
    {
        return TaskType;
    }


    public void setTaskType(String taskType)
    {
        TaskType = taskType;
    }


    public BigDecimal getSlmtId()
    {
        return slmtId;
    }


    public void setSlmtId(BigDecimal slmtId)
    {
        this.slmtId = slmtId;
    }


    public String getSettleRng()
    {
        return settleRng;
    }


    public void setSettleRng(String settleRng)
    {
        this.settleRng = settleRng;
    }


    public List<BigDecimal> getBusiNoticeTaskOidList()
    {
        return busiNoticeTaskOidList;
    }


    public void setBusiNoticeTaskOidList(List<BigDecimal> busiNoticeTaskOidList)
    {
        this.busiNoticeTaskOidList = busiNoticeTaskOidList;
    }


    public BigDecimal getAgentId()
    {
        return agentId;
    }


    public void setAgentId(BigDecimal agentId)
    {
        this.agentId = agentId;
    }


    public List<TaskProcReqDto> getInvestorNoticeTaskOidList()
    {
        return investorNoticeTaskOidList;
    }


    public void setInvestorNoticeTaskOidList(
            List<TaskProcReqDto> investorNoticeTaskOidList)
    {
        this.investorNoticeTaskOidList = investorNoticeTaskOidList;
    }


    public List<BigDecimal> getNoticeTask4SettleList()
    {
        return noticeTask4SettleList;
    }


    public void setNoticeTask4SettleList(List<BigDecimal> noticeTask4SettleList)
    {
        this.noticeTask4SettleList = noticeTask4SettleList;
    }


    public int getTask4SettleNotice()
    {
        return task4SettleNotice;
    }


    public void setTask4SettleNotice(int task4SettleNotice)
    {
        this.task4SettleNotice = task4SettleNotice;
    }


    public BigDecimal getTaskOidNotice31()
    {
        return taskOidNotice31;
    }


    public void setTaskOidNotice31(BigDecimal taskOidNotice31)
    {
        this.taskOidNotice31 = taskOidNotice31;
    }


    public List<BigDecimal> getNoticeTaskOidList1()
    {
        return noticeTaskOidList1;
    }


    public void setNoticeTaskOidList1(List<BigDecimal> noticeTaskOidList1)
    {
        this.noticeTaskOidList1 = noticeTaskOidList1;
    }


    public List<BigDecimal> getTaskOidNotice40()
    {
        return taskOidNotice40;
    }


    public void setTaskOidNotice40(List<BigDecimal> taskOidNotice40)
    {
        this.taskOidNotice40 = taskOidNotice40;
    }


    public List<BigDecimal> getNoticeTaskOidList41011()
    {
        return noticeTaskOidList41011;
    }


    public void setNoticeTaskOidList41011(
            List<BigDecimal> noticeTaskOidList41011)
    {
        this.noticeTaskOidList41011 = noticeTaskOidList41011;
    }

}
