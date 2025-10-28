package com.seeho.service.test;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

public class SendaBillsDataInsert {

    private static final String URL = "jdbc:mysql://192.168.3.60:3306/seeho_test_service?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "root321";

    private static final DateTimeFormatter DT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    // 和你示例 INSERT 完全一致的字段顺序（92列）
    private static final String[] COLS = {
            "bill_code", "is_bag", "bag_code", "rec_time", "rec_site_id", "rec_info", "come_info",
            "scan_time", "scan_site_id", "scan_user_id", "area_id", "customer_id", "account_user_id",
            "bill_date", "bill_time", "rec_weight", "center_weight", "account_weight",
            "first_weight_time", "disp_prov_id", "disp_city_id", "disp_source", "bill_use_site_id",
            "bill_provide_man_id", "bill_provide_man_name", "bill_provide_type", "is_offline_ele",
            "sz_center_fee", "sz_bill_fee", "oper_add_fee", "common_fee", "avg_weight_fee",
            "avg_add_weight_fee", "avg_status", "user_royalty", "two_level_fee", "bill_subsidy",
            "disp_fee", "basic_disp_fee", "disp_add_fee", "bill_cost", "center_rec_fee",
            "center_rec_count", "type", "bill_status", "class_type", "goods_type", "bill_type",
            "return_status", "other_time", "data_source", "sign_time", "sign_site_id", "sign_status",
            "disp_time", "disp_site_id", "disp_weight", "first_center_oper_time",
            "first_center_oper_site_id", "first_center_next_id", "hexiao_man", "hexiao_time",
            "hexiao_fee", "fee_modifie_time", "lock_status", "lock_man", "lock_time", "order_source",
            "user_data_source", "order_code", "order_partner_id", "partner_trade_id", "parcel_weight",
            "bill_version", "sign_disp_fee_version", "center_rec_fee_version", "remark", "editor",
            "msg_type", "fine", "fine_version", "bill_provide_diff", "goods_payment_type",
            "value_added_type", "sender_info", "consignee_info", "reco_order_time", "center_come_info",
            "scan_man_id", "hexiao_user_id", "account_water", "quote_object_id"
    };

    public static void main(String[] args) throws Exception {
        // 自动生成占位符
        String placeholders = String.join(",", Collections.nCopies(COLS.length, "?"));
        String columns = String.join(",", COLS);
        String sql = "INSERT INTO senda_bills (" + columns + ") VALUES (" + placeholders + ")";


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            LocalDateTime base = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

            for (int i = 0; i < 100; i++) {
                LocalDateTime t = base.plusMinutes(i);
                String dt = t.format(DT);
                String d = dt.substring(0, 10);

                // 按上面 COLS 顺序准备 92 个值
                Object[] vals = new Object[]{
                        // 1 bill_code：12位、单调递增
                        "72323097" + String.format("%04d", 1000 + i),
                        0, "", dt, 1000 + i, "rec_info", "come_info",
                        dt, 2000 + i, 3000 + i, 4000 + i, 5000 + i, 6000 + i,
                        d, dt, 1.00, 1.10, 1.20,
                        dt, 10 + i, 20 + i, 1, 7000 + i,
                        8000 + i, "Man" + i, 0, 0,
                        0.10, 0.20, 0.30, 0.40, 0.50,
                        0.60, 0, 0.70, 0.80, 0.90,
                        1.10, 1.20, 1.30, 1.40, 1.50,
                        1, 0, 2, 1, 0, 1,
                        0, dt, 1, dt, 9000 + i, 1,
                        dt, 9100 + i, 2.22, dt,
                        9200 + i, 9300 + i, "H" + i, dt,
                        3.33, dt, 0, 9400 + i, dt, "JD",
                        1, "OC" + i, "OP" + i, "PT" + i, 1.00,
                        1, 1, 1, "remark", "editor",
                        0, 2.22, 1, 0, 1,
                        0, "sender", "consignee", dt, "center_info",
                        9500 + i, 9600 + i, "AW" + i, 9700 + i
                };

                if (vals.length != COLS.length) {
                    throw new IllegalStateException("参数数量不等于列数量: vals=" + vals.length + ", cols=" + COLS.length);
                }

                // 统一 setObject，避免索引错位
                for (int idx = 0; idx < vals.length; idx++) {
                    ps.setObject(idx + 1, vals[idx]);
                }
                ps.addBatch();
            }

            int[] res = ps.executeBatch();
            conn.commit();
            System.out.println("✅ 插入完成，行数：" + Arrays.stream(res).sum());
        }
    }
}
