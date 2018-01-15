package com.sauzny.dbutils.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RandomPersonUtils {
    
    private static final String[] firstName="赵 钱 孙 李 周 吴 郑 王 冯 陈 褚 卫 蒋 沈 韩 杨 朱 秦 尤 许 何 吕 施 张 孔 曹 严 华 金 魏 陶 姜 戚 谢 邹 喻 柏 水 窦 章 云 苏 潘 葛 奚 范 彭 郎 鲁 韦 昌 马 苗 凤 花 方 俞 任 袁 柳 酆 鲍 史 唐 费 廉 岑 薛 雷 贺 倪 汤 滕 殷 罗 毕 郝 邬 安 常 乐 于 时 傅 皮 卞 齐 康 伍 余 元 卜 顾 孟 平 黄 和 穆 萧 尹 姚 邵 湛 汪 祁 毛 禹 狄 米 贝 明 臧 计 伏 成 戴 谈 宋 茅 庞 熊 纪 舒 屈 项 祝 董 梁 杜 阮 蓝 闵 席 季 麻 强 贾 路 娄 危 江 童 颜 郭 梅 盛 林 刁 锺 徐 邱 骆 高 夏 蔡 田 樊 胡 凌 霍 虞 万 支 柯 昝 管 卢 莫 经 房 裘 缪 干 解 应 宗 丁 宣 贲 邓 郁 单 杭 洪 包 诸 左 石 崔 吉 钮 龚 程 嵇 邢 滑 裴 陆 荣 翁 荀 羊 於 惠 甄 麴 家 封 芮 羿 储 靳 汲 邴 糜 松 井 段 富 巫 乌 焦 巴 弓 牧 隗 山 谷 车 侯 宓 蓬 全 郗 班 仰 秋 仲 伊 宫 宁 仇 栾 暴 甘 钭 历 戎 祖 武 符 刘 景 詹 束 龙 叶 幸 司 韶 郜 黎 蓟 溥 印 宿 白 怀 蒲 邰 从 鄂 索 咸 籍 赖 卓 蔺 屠 蒙 池 乔 阳 郁 胥 能 苍 双 闻 莘 党 翟 谭 贡 劳 逄 姬 申 扶 堵 冉 宰 郦 雍 却 璩 桑 桂 濮 牛 寿 通 边 扈 燕 冀 僪 浦 尚 农 温 别 庄 晏 柴 瞿 阎 充 慕 连 茹 习 宦 艾 鱼 容 向 古 易 慎 戈 廖 庾 终 暨 居 衡 步 都 耿 满 弘 匡 国 文 寇 广 禄 阙 东 欧 殳 沃 利 蔚 越 夔 隆 师 巩 厍 聂 晁 勾 敖 融 冷 訾 辛 阚 那 简 饶 空 曾 毋 沙 乜 养 鞠 须 丰 巢 关 蒯 相 查 后 荆 红 游 竺 权 逮 盍 益 桓 公 万俟 司马 上官 欧阳 夏侯 诸葛 闻人 东方 赫连 皇甫 尉迟 公羊 澹台 公冶 宗政 濮阳 淳于 单于 太叔 申屠 公孙 仲孙 轩辕 令狐 钟离 宇文 长孙 慕容 司徒 司空 召 有 舜 叶赫那拉 丛 岳 寸 贰 皇 侨 彤 竭 端 赫 实 甫 集 象 翠 狂 辟 典 良 函 芒 苦 其 京 中 夕 之 章佳 那拉 冠 宾 香 果 依尔根觉罗 依尔觉罗 萨嘛喇 赫舍里 额尔德特 萨克达 钮祜禄 他塔喇 喜塔腊 讷殷富察 叶赫那兰 库雅喇 瓜尔佳 舒穆禄 爱新觉罗 索绰络 纳喇 乌雅 范姜 碧鲁 张廖 张简 图门 太史 公叔 乌孙 完颜 马佳 佟佳 富察 费莫 蹇 称 诺 来 多 繁 戊 朴 回 毓 税 荤 靖 绪 愈 硕 牢 买 但 巧 枚 撒 泰 秘 亥 绍 以 壬 森 斋 释 奕 姒 朋 求 羽 用 占 真 穰 翦 闾 漆 贵 代 贯 旁 崇 栋 告 休 褒 谏 锐 皋 闳 在 歧 禾 示 是 委 钊 频 嬴 呼 大 威 昂 律 冒 保 系 抄 定 化 莱 校 么 抗 祢 綦 悟 宏 功 庚 务 敏 捷 拱 兆 丑 丙 畅 苟 随 类 卯 俟 友 答 乙 允 甲 留 尾 佼 玄 乘 裔 延 植 环 矫 赛 昔 侍 度 旷 遇 偶 前 由 咎 塞 敛 受 泷 袭 衅 叔 圣 御 夫 仆 镇 藩 邸 府 掌 首 员 焉 戏 可 智 尔 凭 悉 进 笃 厚 仁 业 肇 资 合 仍 九 衷 哀 刑 俎 仵 圭 夷 徭 蛮 汗 孛 乾 帖 罕 洛 淦 洋 邶 郸 郯 邗 邛 剑 虢 隋 蒿 茆 菅 苌 树 桐 锁 钟 机 盘 铎 斛 玉 线 针 箕 庹 绳 磨 蒉 瓮 弭 刀 疏 牵 浑 恽 势 世 仝 同 蚁 止 戢 睢 冼 种 涂 肖 己 泣 潜 卷 脱 谬 蹉 赧 浮 顿 说 次 错 念 夙 斯 完 丹 表 聊 源 姓 吾 寻 展 出 不 户 闭 才 无 书 学 愚 本 性 雪 霜 烟 寒 少 字 桥 板 斐 独 千 诗 嘉 扬 善 揭 祈 析 赤 紫 青 柔 刚 奇 拜 佛 陀 弥 阿 素 长 僧 隐 仙 隽 宇 祭 酒 淡 塔 琦 闪 始 星 南 天 接 波 碧 速 禚 腾 潮 镜 似 澄 潭 謇 纵 渠 奈 风 春 濯 沐 茂 英 兰 檀 藤 枝 检 生 折 登 驹 骑 貊 虎 肥 鹿 雀 野 禽 飞 节 宜 鲜 粟 栗 豆 帛 官 布 衣 藏 宝 钞 银 门 盈 庆 喜 及 普 建 营 巨 望 希 道 载 声 漫 犁 力 贸 勤 革 改 兴 亓 睦 修 信 闽 北 守 坚 勇 汉 练 尉 士 旅 五 令 将 旗 军 行 奉 敬 恭 仪 母 堂 丘 义 礼 慈 孝 理 伦 卿 问 永 辉 位 让 尧 依 犹 介 承 市 所 苑 杞 剧 第 零 谌 招 续 达 忻 六 鄞 战 迟 候 宛 励 粘 萨 邝 覃 辜 初 楼 城 区 局 台 原 考 妫 纳 泉 老 清 德 卑 过 麦 曲 竹 百 福 言 第五 佟 爱 年 笪 谯 哈 墨 南宫 赏 伯 佴 佘 牟 商 西门 东门 左丘 梁丘 琴 后 况 亢 缑 帅 微生 羊舌 海 归 呼延 南门 东郭 百里 钦 鄢 汝 法 闫 楚 晋 谷梁 宰父 夹谷 拓跋 壤驷 乐正 漆雕 公西 巫马 端木 颛孙 子车 督 仉 司寇 亓官 鲜于 锺离 盖 逯 库 郏 逢 阴 薄 厉 稽 闾丘 公良 段干 开 光 操 瑞 眭 泥 运 摩 伟 铁".split(" "); 
    private static final String female="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";  
    private static final String male="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";  
    private static final String[] road="重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路,河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路,泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道,古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路,荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场,郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路,山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路,西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路,台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场,台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街,嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街,滕县路,钜野广场,观城路,汶上大厦,朝城路,滋阳街,邹县广场,濮县街,磁山路,汶水街,西藏路,城武大厦,团岛路,南阳街,广州路,东平街,枣庄广场,贵州街,费县路,南海大厦,登州路,文登广场,信号山支路,延安一街,信号山路,兴安支街,福山支广场,红岛支大厦,莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,观象二路,吴县二大厦,莱芜一广场,金口二街,海阳路,龙口街,恒山路,鱼山广场,掖县路,福山大厦,红岛路,常州街,大学广场,龙华街,齐河路,莱阳街,黄县路,张店大厦,祚山路,苏州街,华山路,伏龙街,江苏广场,龙江街,王村路,琴屿大厦,齐东路,京山广场,龙山路,牟平街,延安三路,延吉街,南京广场,东海东大厦,银川西路,海口街,山东路,绍兴广场,芝泉路,东海中街,宁夏路,香港西大厦,隆德广场,扬州街,郧阳路,太平角一街,宁国二支路,太平角二广场,天台东一路,太平角三大厦,漳州路一路,漳州街二街,宁国一支广场,太平角六街,太平角四路,天台东二街,太平角五路,宁国三大厦,澳门三路,江西支街,澳门二路,宁国四街,大尧一广场,咸阳支街,洪泽湖路,吴兴二大厦,澄海三路,天台一广场,新湛二路,三明北街,新湛支路,湛山五街,泰州三广场,湛山四大厦,闽江三路,澳门四街,南海支路,吴兴三广场,三明南路,湛山二街,二轻新村镇,江南大厦,吴兴一广场,珠海二街,嘉峪关路,高邮湖街,湛山三路,澳门六广场,泰州二路,东海一大厦,天台二路,微山湖街,洞庭湖广场,珠海支街,福州南路,澄海二街,泰州四路,香港中大厦,澳门五路,新湛三街,澳门一路,正阳关街,宁武关广场,闽江四街,新湛一路,宁国一大厦,王家麦岛,澳门七广场,泰州一路,泰州六街,大尧二路,青大一街,闽江二广场,闽江一大厦,屏东支路,湛山一街,东海西路,徐家麦岛函谷关广场,大尧三路,晓望支街,秀湛二路,逍遥三大厦,澳门九广场,泰州五街,澄海一路,澳门八街,福州北路,珠海一广场,宁国二路,临淮关大厦,燕儿岛路,紫荆关街,武胜关广场,逍遥一街,秀湛四路,居庸关街,山海关路,鄱阳湖大厦,新湛路,漳州街,仙游路,花莲街,乐清广场,巢湖街,台南路,吴兴大厦,新田路,福清广场,澄海路,莆田街,海游路,镇江街,石岛广场,宜兴大厦,三明路,仰口街,沛县路,漳浦广场,大麦岛,台湾街,天台路,金湖大厦,高雄广场,海江街,岳阳路,善化街,荣成路,澳门广场,武昌路,闽江大厦,台北路,龙岩街,咸阳广场,宁德街,龙泉路,丽水街,海川路,彰化大厦,金田路,泰州街,太湖路,江西街,泰兴广场,青大街,金门路,南通大厦,旌德路,汇泉广场,宁国路,泉州街,如东路,奉化街,鹊山广场,莲岛大厦,华严路,嘉义街,古田路,南平广场,秀湛路,长汀街,湛山路,徐州大厦,丰县广场,汕头街,新竹路,黄海街,安庆路,基隆广场,韶关路,云霄大厦,新安路,仙居街,屏东广场,晓望街,海门路,珠海街,上杭路,永嘉大厦,漳平路,盐城街,新浦路,新昌街,高田广场,市场三街,金乡东路,市场二大厦,上海支路,李村支广场,惠民南路,市场纬街,长安南路,陵县支街,冠县支广场,小港一大厦,市场一路,小港二街,清平路,广东广场,新疆路,博平街,港通路,小港沿,福建广场,高唐街,茌平路,港青街,高密路,阳谷广场,平阴路,夏津大厦,邱县路,渤海街,恩县广场,旅顺街,堂邑路,李村街,即墨路,港华大厦,港环路,馆陶街,普集路,朝阳街,甘肃广场,港夏街,港联路,陵县大厦,上海路,宝山广场,武定路,长清街,长安路,惠民街,武城广场,聊城大厦,海泊路,沧口街,宁波路,胶州广场,莱州路,招远街,冠县路,六码头,金乡广场,禹城街,临清路,东阿街,吴淞路,大港沿,辽宁路,棣纬二大厦,大港纬一路,贮水山支街,无棣纬一广场,大港纬三街,大港纬五路,大港纬四街,大港纬二路,无棣二大厦,吉林支路,大港四街,普集支路,无棣三街,黄台支广场,大港三街,无棣一路,贮水山大厦,泰山支路,大港一广场,无棣四路,大连支街,大港二路,锦州支街,德平广场,高苑大厦,长山路,乐陵街,临邑路,嫩江广场,合江路,大连街,博兴路,蒲台大厦,黄台广场,城阳街,临淄路,安邱街,临朐路,青城广场,商河路,热河大厦,济阳路,承德街,淄川广场,辽北街,阳信路,益都街,松江路,流亭大厦,吉林路,恒台街,包头路,无棣街,铁山广场,锦州街,桓台路,兴安大厦,邹平路,胶东广场,章丘路,丹东街,华阳路,青海街,泰山广场,周村大厦,四平路,台东西七街,台东东二路,台东东七广场,台东西二路,东五街,云门二路,芙蓉山村,延安二广场,云门一街,台东四路,台东一街,台东二路,杭州支广场,内蒙古路,台东七大厦,台东六路,广饶支街,台东八广场,台东三街,四平支路,郭口东街,青海支路,沈阳支大厦,菜市二路,菜市一街,北仲三路,瑞云街,滨县广场,庆祥街,万寿路,大成大厦,芙蓉路,历城广场,大名路,昌平街,平定路,长兴街,浦口广场,诸城大厦,和兴路,德盛街,宁海路,威海广场,东山路,清和街,姜沟路,雒口大厦,松山广场,长春街,昆明路,顺兴街,利津路,阳明广场,人和路,郭口大厦,营口路,昌邑街,孟庄广场,丰盛街,埕口路,丹阳街,汉口路,洮南大厦,桑梓路,沾化街,山口路,沈阳街,南口广场,振兴街,通化路,福寺大厦,峄县路,寿光广场,曹县路,昌乐街,道口路,南九水街,台湛广场,东光大厦,驼峰路,太平山,标山路,云溪广场,太清路".split(",");  
    private static final String[] email_suffix="@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");  
    private static final String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");  
    
    private static String getOneFromString(String str){
        return String.valueOf(str.charAt((int)(Math.random()*str.length())));
    }
    
    private static String getOneFromArray(String[] strs){
        return strs[(int)(Math.random()*strs.length)];
    }
  
    /** 
     * 返回Email 
     * @param lMin 最小长度 
     * @param lMax 最大长度 
     * @return 
     */  
    public static String getEmail(int lMin,int lMax) {  
        
        StringBuffer sb = new StringBuffer();   
        sb.append(RandomStringUtils.randomAlphanumeric(lMin, lMax));
        sb.append(getOneFromArray(email_suffix));  
        
        return sb.toString();     
    }  
  
    /** 
     * 返回手机号码 
     */ 
    public static String getTel() {  
        String first = getOneFromArray(telFirst);  
        String second=String.valueOf(RandomUtils.nextInt(1,888)+10000).substring(1);  
        String third=String.valueOf(RandomUtils.nextInt(1,9100)+10000).substring(1);  
        return first+second+third;  
    }  
    
    public static int getGender_int(){
        return RandomUtils.nextInt(0,2);
    }
    
    public static String getGender_zh(int gender){
        if(gender==0){
            return "女";
        }else{
            return "男";
        }
    }
    
    public static String getGender_zh(){
        
        if(getGender_int()==0){
            return "女";
        }else{
            return "男";
        }
    }
    
    public static String getGender_en(int gender){
        
        if(gender==0){
            return "female";
        }else{
            return "male";
        }
    }
    
    public static String getGender_en(){
        
        if(getGender_int()==0){
            return "female";
        }else{
            return "male";
        }
    }
    
    /** 
     * 返回中文姓名 
     */  
    
    public static String getChineseName(){
        int gender = getGender_int();
        return getChineseName(gender);
    }
    
    /**
     * @描述: TODO
     */
    
    
    public static String getChineseName(int gender) {  
         
        String first=getOneFromArray(firstName);
         
        String second=""; 
        String third="";  
        
        if(gender == 0){
            second += getOneFromString(female);  
        }else{
            second += getOneFromString(male); 
        }
        
        int hasThird=RandomUtils.nextInt(0,2); 
        if(hasThird==1){  
            if(gender == 0){
                third += getOneFromString(female);  
            }else{
                third += getOneFromString(male); 
            } 
        }  
        return first+second+third;  
    }  
      
    /** 
     * 返回地址 
     * @return 
     */  
    public static String getRoad() {  
        
        String first=getOneFromArray(road); 
        String second=String.valueOf(RandomUtils.nextInt(11,150))+"号";  
        String third="-"+RandomUtils.nextInt(1,20)+"-"+RandomUtils.nextInt(1,10);  
        return first+second+third;  
    }  
      
    /** 
     * 数据封装 
     * @return 
     */  
    private static Map<String, String> getOne() { 
        
        int gender = getGender_int();
        
        Map<String, String> map=new HashMap<String, String>();  
        map.put("name", getChineseName(gender));  
        map.put("sex", getGender_zh(gender));
        map.put("road", getRoad());  
        map.put("tel", getTel());  
        map.put("email", getEmail(6,9));  
        
        return map;  
    }  
      
    public static void main(String[] args) {  
        for (int i = 0; i < 10; i++) {  
            System.out.println(getOne());  
        }  
    }  
}
