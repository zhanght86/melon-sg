<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>


<script type="text/javascript">
	$(document).ready(function() {
		var url = 'http://10.70.1.10:9200/las-syslog-2014.10.30/_search';
		var datas = {
			"facets" : {
				"terms" : {
					"terms" : {
						"field" : "eventType",
						"size" : 10,
						"order" : "count",
						"exclude" : []
					},
					"facet_filter" : {
						"fquery" : {
							"query" : {
								"filtered" : {
									"query" : {
										"bool" : {
											"should" : [ {
												"query_string" : {
													"query" : "*"
												}
											} ]
										}
									},
									"filter" : {
										"bool" : {
											"must" : [ {
												"range" : {
													"@timestamp" : {
														"from" : 1414639547855,
														"to" : 1414661147855
													}
												}
											} ]
										}
									}
								}
							}
						}
					}
				}
			},
			"size" : 0
		};
		
		$.post(url, datas, function(data, extStatus, jqXhr) {
			console.debug(data);
		});
	});
</script>