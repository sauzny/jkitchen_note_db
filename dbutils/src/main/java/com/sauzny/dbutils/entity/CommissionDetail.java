package com.sauzny.dbutils.entity;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.google.common.collect.Lists;
import com.sauzny.dbutils.entity.randomdata.RandomDateTimeUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommissionDetail  implements CharSequence{
    
    @Override
    public int length() {
        // TODO Auto-generated method stub
        return this.toString().length();
    }

    @Override
    public char charAt(int index) {
        // TODO Auto-generated method stub
        return this.toString().charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        // TODO Auto-generated method stub
        return this.toString().subSequence(start, end);
    }
    
    public CommissionDetail() {
        super();
    }
    /*
id  bigint(20) unsigned
user_id bigint(20)
user_level  int(4)
commission_type int(4)
commission_id   varchar(64)
pub_share_pre_fee   int(11)
commission  int(11)
pay_price   varchar(32)
tk_user_id  int(11)
earning_time    datetime
pub_share_pre_total_fee varchar(32)
total_commission_fee    varchar(32)
commission_state    int(11)
create_time datetime
last_update_time    timestamp
    */
    Long id;
    Long userId;
    // 用户级别(0淘客，1一级，2二级代理, 10直接，11间接运营商)
    Integer userLevel;
    // 佣金类型(1订单佣金，2代理费)
    Integer commissionType;
    String commissionId;
    Integer pubSharePreFee;
    Integer commission;
    String pay_price;
    Integer tk_user_id;
    LocalDateTime earning_time;
    String pub_share_pre_total_fee;
    String total_commission_fee;
    // 0: 初始, 3：结算
    Integer commission_state;
    LocalDateTime createTime;
    LocalDateTime lastUpdateTime;
    
    
    
    public static CommissionDetail one(){
        
        int[] levels = {0, 1, 2, 10, 11};
        
        Integer userLevel = levels[RandomUtils.nextInt(0,5)];
        
        Long userId = RandomUtils.nextLong(100, 200);
        Integer tk_user_id = null;
        if(userLevel == 0){
            tk_user_id = userId.intValue();
        }else{
            tk_user_id = RandomUtils.nextInt(300, 600);
        }
        Integer pubSharePreFee = RandomUtils.nextInt(10, 30);
        Integer commission = pubSharePreFee - RandomUtils.nextInt(1, 10);
        
        LocalDateTime start = LocalDateTime.parse("2018-01-07T00:00:00.000");
        LocalDateTime end = LocalDateTime.parse("2018-03-08T00:00:00.000");
        
        LocalDateTime localDateTime = RandomDateTimeUtils.getTime(start, end);
        
        return new CommissionDetail(
                null,
                userId,
                userLevel,
                RandomUtils.nextInt(1, 3),
                String.valueOf(RandomUtils.nextLong(10000, 200000)),
                pubSharePreFee,
                commission,
                String.valueOf(RandomUtils.nextInt(30, 100)),
                tk_user_id,
                localDateTime,
                String.valueOf(RandomUtils.nextLong(200, 300)),
                String.valueOf(RandomUtils.nextLong(100, 200)),
                Integer.valueOf(0),
                localDateTime,
                localDateTime
                );
    }
    
    public static void main(String[] args) throws IOException {
        
        List<CommissionDetail> lines = Lists.newArrayList();
        
        for(int i=0; i<500000; i++){
            lines.add(CommissionDetail.one());
        }
        
        Files.write(Paths.get("/data/demo/commissionDetail.txt"), lines, StandardCharsets.UTF_8 ,StandardOpenOption.CREATE);
        
        //Files.write(Paths.get("/data/demo/commissionDetail.txt"), list, StandardOpenOption.APPEND);
    }

    @Override
    public String toString() {
        return userId + "\t" + userLevel + "\t" + commissionType + "\t" + commissionId + "\t" + pubSharePreFee + "\t" + commission + "\t" + pay_price + "\t" + tk_user_id + "\t" + earning_time.toString().replace("T", " ") + "\t" + pub_share_pre_total_fee + "\t" + total_commission_fee + "\t" + commission_state + "\t" + createTime.toString().replace("T", " ") + "\t" + lastUpdateTime.toString().replace("T", " ");
    }

}
