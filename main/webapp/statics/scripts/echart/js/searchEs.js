var searchEs ={
		client : new $.es.Client({
			  hosts: '10.70.1.10:9200'
			}),
		/**
		 * 根据具体字段分组查询，
		 * @param name 分组字段名,params ：查询条件参数;index：索引；type：数据类型,from:开始时间,to:结束时间,fn:回调函数方法
		 * @return 分组对象数组
		 */
		groupByName:function(index,type,name,params,from,to,size,fn){
				this.client.search({
					type:type,
					body: {
						 "facets": {
							    "terms": {
							      "terms": {
							        "field": name,
							        "size": size,
							        "order": "count",
							        "exclude": []
							      },
							      "facet_filter": {
							        "fquery": {
							          "query": {
							            "filtered": {
							              "query": {
							                "bool": {
							                  "should": [
							                    {
							                      "query_string": {
							                        "query":searchEs.paramString(params)
							                      }
							                    }
							                  ]
							                }
							              },
							              "filter": {
							                  "bool": {
							                    "must": [
							                      {
							                        "range": {
							                          "@timestamp": {
							                            "from": from,
							                            "to": to
							                          }
							                        }
							                      }
							                    ]
							                  }
							                }
							            }
							          }
							        }
							      }
							    }
							  },
							  "size": size
						
					  }
					},function(err,res){
						if(err){
							fn.call(null,[]);
						}
						if(res){
							arrays = res.facets.terms.terms;
							fn.call(null,arrays);
						}
						
					})
		},
		/**
		 * @param params ：查询条件参数;index：索引；type：数据类型,fn:回调函数方法
		 * @return 制定索引，类型的数据集合 
		 */
		searchList:function(index,type,params,from,to,size,fn){
			this.client.search({
				type:type,
				replication:"async",
				body:{
					"query": {
					    "filtered": {
					      "query": {
					        "bool": {
					          "should": [
					            {
					              "query_string": {
					                "query": searchEs.paramString(params)
					              }
					            }
					          ]
					        }
					      },
			              "filter": {
			                  "bool": {
			                    "must": [
			                      {
			                        "range": {
			                          "@timestamp": {
			                            "from": from,
			                            "to": to
			                          }
			                        }
			                      }
			                    ]
			                  }
			             }
					   }
					},
					"size": size
				}
			},function(err,res){
				if(err){
					fn.call(null,[]);
				}
				if(res){
					var arrays = res.hits.hits;
					fn.call(null,arrays);
				}
			})
		},
		/**
		 * 根据参数数组得到对应的参数字符
		 */
		paramString:function(params){
			var paramsValue = "";
			$.each(params,function(i,v){
					if(paramsValue.length==0){
						paramsValue += v.name+":"+v.value;
					}else{
						if(v.node=="1"){
							paramsValue += " OR " +v.name+":"+v.value;
						}else{
							paramsValue += " AND " +v.name+":"+v.value;
						}
					}
			});
			if(paramsValue === "") {
				paramsValue = "*";
			}
			return paramsValue;
		}
		
}