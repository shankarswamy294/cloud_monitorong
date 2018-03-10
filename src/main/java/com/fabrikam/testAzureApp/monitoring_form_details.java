package com.fabrikam.testAzureApp;

public class monitoring_form_details {
	private String startDateTime;
	private String endDateTime;
	private String metricType;
	private String AggregationType;
	private String interval;
	private String sub_id;
	
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getAggregationType() {
		return AggregationType;
	}
	public void setAggregationType(String aggregationType) {
		AggregationType = aggregationType;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getMetricType() {
		return metricType;
	}
	public void setMetricType(String metricType) {
		this.metricType = metricType;
	}
	
}
