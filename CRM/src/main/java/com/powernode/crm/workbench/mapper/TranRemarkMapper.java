package com.powernode.crm.workbench.mapper;

import com.powernode.crm.workbench.domain.ClueRemark;
import com.powernode.crm.workbench.domain.TranRemark;

import java.util.List;

public interface TranRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Nov 03 11:56:51 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Nov 03 11:56:51 CST 2023
     */
    int insert(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Nov 03 11:56:51 CST 2023
     */
    int insertSelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Nov 03 11:56:51 CST 2023
     */
    TranRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Nov 03 11:56:51 CST 2023
     */
    int updateByPrimaryKeySelective(TranRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_remark
     *
     * @mbggenerated Fri Nov 03 11:56:51 CST 2023
     */
    int updateByPrimaryKey(TranRemark record);

    /**
     * 批量保存创建的交易备注
     * @param list
     * @return
     */
    int insertTranRemarkByList(List<TranRemark> list);

    /**
     * 根据tranId查询该交易下所有备注
     * @param tranId
     * @return
     */
    List<TranRemark> selectTranRemarkForDetailByTranId(String tranId);

    /**
     *保存创建的交易备注
     * @param tranRemark
     * @return
     */
    int insertTranRemark(TranRemark tranRemark);

    /**
     *根据id删除交易备注
     * @param id
     * @return
     */
    int deleteTranRemark(String id);

    /**
     *保存修改的交易备注
     * @param tranRemark
     * @return
     */
    int updateTranRemark(TranRemark tranRemark);

}
