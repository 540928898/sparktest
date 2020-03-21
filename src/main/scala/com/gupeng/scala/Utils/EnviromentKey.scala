//package com.gupeng.scala.Utils
//
//import java.util
//
//import com.typesafe.config.ConfigFactory
//
///**
//  * 这个trait填写所需配置信息
//  */
//
//trait EnvironmentKey {
////  val config: Predef.Map[String, String] = ConfigFactory.load()
////  val isLocal: Boolean = "Mac OS X" == config.getOrElse("os.name", "")
////  val user_name: String = config.getOrElse("user.name", "")
////
////  val checkpoint: String = config.getOrElse("spark.checkpoint", "hdfs:///tmp/checkpoint/")
////  val errorDir: String = config.getOrElse("spark.errorDir", "hdfs:///tmp/streamingerror/")
////
////  val hbaseConfig: Map[String, String] = config.filterKeys(_.contains("hbase"))
//
//  val kafkaConsumerConfig: Map[String, String] = config.filterKeys(_.contains("kafka.consumer")).map(i => i._1.replace("kafka.consumer.", "") -> i._2)
//
//  val kafkaProducerConfig: Map[String, String] = config.filterKeys(_.contains("kafka.producer")).map(i => i._1.replace("kafka.producer.", "") -> i._2)
////
////  val influxDBConfig: Map[String, String] = config.filterKeys(_.contains("influxDB")).map(i => i._1.replace("influxDB.", "") -> i._2)
////
////  val influxDBUrl: String = influxDBConfig.getOrElse("url", "http://10.138.61.35:8086")
////
////  val influxDBUsername: String = influxDBConfig.getOrElse("username", "root")
////
////  val influxDBPassword: String = influxDBConfig.getOrElse("password", "root")
////
////  val corpId: String = config.getOrElse("wechat.corpId", "ww1c444737dff65488")
////
////  val corpSecret: String = config.getOrElse("wechat.corpSecret", "B89FtTYB3J0NJdhBiisaREYx3J31DWFFluz6UJOOPow")
////
////  val weChatMembers: List[String] = config.getOrElse("wechat.members", "kuangwenhao,jiangkaiyue,limeng,ZhaoJinChun,wangxinlin,mengkaiqiang").split(",").toList
//
//  val table_path: Map[String, (String, String)] = Map("dds_jxl_mobiletype" -> (s"/Users/$user_name/Downloads/hive_table/dds_jxl_mobiletype_parquet", "hdfs:///user/hive/warehouse/dds.db/dds_jxl_mobiletype_parquet"),
//    "dds_jxl_mobiletype_v2" -> (s"/Users/$user_name/Downloads/hive_table/dds_jxl_mobiletype_v2_parquet", "hdfs:///user/hive/warehouse/dds.db/dds_jxl_mobiletype_v2_parquet"),
//    "dds_crm_mobilearea" -> (s"/Users/$user_name/Downloads/hive_table/dds_crm_mobilearea_parquet", "hdfs:///user/hive/warehouse/dds.db/dds_crm_mobilearea_parquet"),
//    "rdm_date_dim" -> (s"/Users/$user_name/Downloads/hive_table/rdm_date_dim", "hdfs:///user/hive/warehouse/rdm.db/rdm_date_dim_2019"),
//    "rdm_sdk_model_2" -> (s"/Users/$user_name/Downloads/hive_table/rdm_sdk_model_2_parquet", "hdfs:///user/hive/warehouse/test.db/rdm_sdk_model_2_parquet"))
//
//  def getMap: util.HashMap[String, Array[String]] = {
//    val map = new util.HashMap[String, Array[String]]
//    map.put("crd_cd_asrrepay", Array("report_id", "organ_name", "latest_assurer_repay_date", "money", "latest_repay_date", "balance", "time_stamp", "timestamp"))
//    map.put("crd_cd_assetdpst", Array("report_id", "organ_name", "get_time", "money", "latest_repay_date", "balance", "time_stamp", "timestamp"))
//    map.put("crd_cd_guarantee", Array("report_id", "organ_name", "contract_money", "begin_date", "end_date", "guarantee_money", "guarantee_balance", "class5_state", "billing_date", "time_stamp", "timestamp"))
//    map.put("crd_cd_guarantzeesummery", Array("report_id", "guaranteenum", "guaranteemoney", "principalbalance", "gettime"))
//    map.put("crd_cd_ln", Array("number", "report_id", "cue", "state", "finance_org", "account_dw", "type_dw", "currency", "open_date", "end_date", "credit_limit_amount", "guarantee_type", "payment_rating", "payment_cyc", "state_end_date", "state_end_month", "class5_state", "balance", "remain_payment_cyc", "scheduled_payment_amount", "scheduled_payment_date", "actual_payment_amount", "recent_pay_date", "curr_overdue_cyc", "curr_overdue_amount", "overdue31_to60_amount", "overdue61_to90_amount", "overdue91_to180_amount", "overdue_over180_amount", "payment_state_begin_month", "payment_state_end_month", "payment_state", "overdue_record_begin_month", "overdue_record_end_month", "time_stamp", "issued_time", "issued_agency", "loan_amount", "loan_kind", "guar_kind", "loan_period", "repay_mothed", "timestamp", "bh", "d24", "d23", "d22", "d21", "d20", "d19", "d18", "d17", "d16", "d15", "d14", "d13", "d12", "d11", "d10", "d9", "d8", "d7", "d6", "d5", "d4", "d3", "d2", "d1", "creditcardno", "settlementdate", "loadbalance", "gettime", "financetype"))
//    map.put("crd_cd_ln_ovd", Array("number", "month_dw", "last_months", "amount", "time_stamp", "timestamp", "report_id"))
//    map.put("crd_cd_ln_spl", Array("number", "type_dw", "get_time", "changing_months", "changing_amount", "content", "time_stamp", "timestamp", "report_id"))
//    map.put("crd_cd_lnd", Array("number", "report_id", "cue", "state", "finance_org", "account_dw", "currency", "open_date", "credit_limit_amount", "guarantee_type", "state_end_date", "state_end_month", "share_credit_limit_amount", "used_credit_limit_amount", "latest6_month_used_avg_amount", "used_highest_amount", "scheduled_payment_date", "scheduled_payment_amount", "actual_payment_amount", "recent_pay_date", "curr_overdue_cyc", "curr_overdue_amount", "overdue31_to60_amount", "overdue61_to90_amount", "overdue91_to180_amount", "overdue_over180_amount", "payment_state_begin_month", "payment_state_end_month", "payment_state", "overdue_record_begin_month", "overdue_record_end_month", "time_stamp", "icr_issued_time", "icr_issued_agency", "icr_curtype", "icr_credit_line", "timestamp", "bh", "d24", "d23", "d22", "d21", "d20", "d19", "d18", "d17", "d16", "d15", "d14", "d13", "d12", "d11", "d10", "d9", "d8", "d7", "d6", "d5", "d4", "d3", "d2", "d1", "creditcardno", "cardtype", "cancellationdate", "accountbalance", "gettime", "financetype", "overdue_cyc"))
//    map.put("crd_cd_lnd_ovd", Array("number", "month_dw", "last_months", "amount", "time_stamp", "timestamp", "report_id"))
//    map.put("crd_cd_overduebreake", Array("report_id", "badbebtnum", "badbebtmoney", "assetdisposalnum", "assetdisposalbalance", "guarantorcompensatorynum", "guarantorcompensatorybalance", "gettime"))
//    map.put("crd_cd_stn_ovd", Array("month_dw", "last_months", "amount", "time_stamp", "timestamp", "report_id"))
//    map.put("crd_cd_stncard", Array("report_id", "cue", "state", "finance_org", "account_dw", "currency", "open_date", "credit_limit_amount", "guarantee_type", "state_end_date", "state_end_month", "share_credit_limit_amount", "used_credit_limit_amount", "latest6_month_used_avg_amount", "used_highest_amount", "scheduled_payment_date", "scheduled_payment_amount", "actual_payment_amount", "recent_pay_date", "curr_overdue_cyc", "curr_overdue_amount", "overdue31_to60_amount", "overdue61_to90_amount", "overdue91_to180_amount", "overdue_over180_amount", "payment_state_begin_month", "payment_state_end_month", "payment_state", "overdue_record_begin_month", "overdue_record_end_month", "time_stamp", "qcr_issued_time", "qcr_issued_agency", "qcr_curtype", "qcr_credit_line", "timestamp", "bh", "creditcardno", "cardtype", "cancellationdate", "accountbalance", "gettime", "financetype"))
//    map.put("crd_hd_report", Array("record_date", "report_id", "report_sn", "query_time", "report_create_time", "name", "cert_type", "cert_no", "query_reason", "query_format", "query_org", "user_code", "query_result_cue", "time_stamp", "importtime", "bh", "timestamp", "creditcardno", "busidentitycard", "reportstate", "evaluationresult"))
//    map.put("crd_is_creditcue", Array("report_id", "house_loan_count", "other_loan_count", "first_loan_open_month", "loancard_count", "first_loancard_open_month", "standard_loancard_count", "first_sl_open_month", "announce_count", "dissent_count", "score", "score_month", "time_stamp", "timestamp", "bh", "creditcardno", "personalloancard_count"))
//    map.put("crd_is_ovdsummary", Array("report_id", "type_dw", "count_dw", "months", "highest_oa_per_mon", "max_duration", "time_stamp", "timestamp", "bh", "creditcardno"))
//    map.put("crd_is_sharedebt", Array("report_id", "type_dw", "finance_corp_count", "finance_org_count", "account_count", "credit_limit", "max_credit_limit_per_org", "min_credit_limit_per_org", "balance", "used_credit_limit", "latest_6m_used_avg_amount", "time_stamp", "timestamp", "bh", "creditcardno"))
//    map.put("crd_pi_accfund", Array("report_id", "area", "register_date", "first_month", "to_month", "state", "pay", "own_percent", "com_percent", "organ_name", "get_time", "time_stamp", "timestamp"))
//    map.put("crd_pi_endinsdpt", Array("report_id", "area", "register_date", "month_duration", "work_date", "state", "own_basic_money", "money", "organ_name", "pause_reason", "get_time", "time_stamp", "timestamp"))
//    map.put("crd_pi_identity", Array("report_id", "gender", "birthday", "marital_state", "mobile", "office_telephone_no", "home_telephone_no", "edu_level", "edu_degree", "post_address", "registered_address", "mate_name", "mate_cert_type", "mate_cert_no", "mate_employer", "mate_telephone_no", "time_stamp", "timestamp", "havestatement", "havemark"))
//    map.put("crd_pi_professnl", Array("report_id", "employer", "employer_address", "occupation", "industry", "duty", "title_dw", "start_year", "get_time", "time_stamp", "timestamp"))
//    map.put("crd_pi_residence", Array("report_id", "address", "residence_type", "get_time", "time_stamp", "timestamp"))
//    map.put("crd_pi_taxarrear", Array("report_id", "organ_name", "tax_arrea_amount", "tax_arrear_date", "time_stamp", "timestamp"))
//    map.put("crd_pi_telpnt", Array("report_id", "organ_name", "type_dw", "register_date", "state", "arrear_money", "arrear_months", "status24", "get_time", "time_stamp", "timestamp"))
//    map.put("crd_pi_vehicle", Array("report_id", "engine_code", "license_code", "brand", "car_type", "use_character", "state", "pledge_flag", "get_time", "time_stamp", "timestamp"))
//    map.put("crd_qr_recorddtlinfo", Array("report_id", "query_date", "querier", "query_reason", "time_stamp"))
//    map.put("crd_qr_reordsmr", Array("report_id", "type_id", "reason", "sum_dw", "time_stamp", "timestamp"))
//    map
//  }
//
//  def getTransMap: util.HashMap[String, String] = {
//    val map = new util.HashMap[String, String]
//    map.put("编号", "number")
//    map.put("身份信息", "crd_pi_identity")
//    map.put("配偶信息", "crd_pi_identity")
//    map.put("居住信息", "crd_pi_residence")
//    map.put("职业信息", "crd_pi_professnl")
//    map.put("信用提示", "crd_is_creditcue")
//    map.put("中征信评分", "crd_is_creditcue")
//    map.put("逾期及违约信息概要", "crd_cd_overduebreake")
//    map.put("逾期(透支)信息汇总", "crd_is_ovdsummary")
//    map.put("未结清贷款信息汇总", "crd_is_sharedebt")
//    map.put("未销户贷记卡信息汇总", "crd_is_sharedebt")
//    map.put("未销户准贷记卡信息汇总", "crd_is_sharedebt")
//    map.put("对外担保信息汇总", "crd_cd_guaranteesummery")
//    map.put("资产处置信息", "crd_cd_assetdpst")
//    map.put("保证人代偿信息", "crd_cd_asrrepay")
//    map.put("贷款", "crd_cd_ln")
//    map.put("贷记卡", "crd_cd_lnd")
//    map.put("准贷记卡", "crd_cd_stncard")
//    map.put("对外担保信息", "crd_cd_guarantee")
//    map.put("欠税记录", "crd_pi_taxarrear")
//    map.put("民事判决记录", "crd_pi_civiljdgm")
//    map.put("强制执行记录", "crd_pi_forceexctn")
//    map.put("行政处罚记录", "crd_pi_adminpnshm")
//    map.put("住房公积金参缴记录", "crd_pi_accfund")
//    map.put("养老保险金缴存记录", "crd_pi_endinsdpt")
//    map.put("养老保险金发放记录", "crd_pi_endinsdlr")
//    map.put("低保救助记录", "crd_pi_salvation")
//    map.put("执业资格记录", "crd_pi_competence")
//    map.put("行政奖励记录", "crd_pi_adminaward")
//    map.put("车辆交易和抵押记录", "crd_pi_vehicle")
//    map.put("电信缴费记录", "crd_pi_telpnt")
//    map.put("本人声明", "crd_an_ancinfo")
//    map.put("五 本人声明", "crd_an_ancinfo")
//    map.put("异议标注", "crd_an_dstinfo")
//    map.put("信贷审批查询记录明细", "crd_qr_recorddtlinfo")
//    map.put("特殊交易", "crd_cd_ln_spl")
//    map.put("逾期/透支记录", "crd_cd_ln_ovd")
//    map.put("查询记录汇总", "crd_qr_reordsmr")
//    map.put("贷记卡特殊交易", "crd_cd_lnd_spl")
//    map.put("贷记卡逾期/透支记录", "crd_cd_lnd_ovd")
//    map.put("贷款特殊交易", "crd_cd_ln_spl")
//    map.put("贷款逾期/透支记录", "crd_cd_ln_ovd")
//    map.put("准贷记卡特殊交易", "crd_cd_stn_spl")
//    map.put("准贷记卡逾期/透支记录", "crd_cd_stn_ovd")
//    map.put("逾期（透支）信息汇总", "crd_is_ovdsummary")
//    map.put("对外贷款担保信息", "crd_cd_guarantee")
//    map.put("对外信用卡担保信息", "crd_cd_guarantee")
//    map.put("征信报告主表", "crd_hd_report")
//    map
//  }
//}
