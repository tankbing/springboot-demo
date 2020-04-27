package com.tankdev.hikari.entity;

import lombok.Data;
import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.UpdateIgnore;
import org.beetl.sql.core.annotatoin.UpdateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * @author javadev.top
 *
 * @since 2020-04-27 17:50
 */
@Data
public class BaseEntity extends TailBean implements Serializable {
	/**
	 * 创建时间
	 */
	@UpdateIgnore
	@UpdateTime
	private Date createDate;

	/**
	 * 更新时间
	 */
	@UpdateTime
	private Date updateDate;

}
