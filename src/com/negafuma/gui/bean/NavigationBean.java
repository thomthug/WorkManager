package com.negafuma.gui.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class NavigationBean {
	 private String productId;
	 private Integer workId;
	 private String workTitle;

	    public String getProductId() {
	        return productId;
	    }

	    public void setProductId(String productId) {
	        this.productId = productId;
	    }

		public Integer getWorkId() {
			return workId;
		}

		public void setWorkId(Integer workId) {
			this.workId = workId;
		}

		public String getWorkTitle() {
			return workTitle;
		}

		public void setWorkTitle(String workTitle) {
			this.workTitle = workTitle;
		}
}
