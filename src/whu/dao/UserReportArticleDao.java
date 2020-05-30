package whu.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import whu.utils.JDBCUtils;

public class UserReportArticleDao {
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());

    public boolean addReportRecord(int articleID, int reportUserID, String report){
        String sql = "insert into user_report_article (articleID, reportUserID, report) values (?, ?, ?)";
        try {
            return template.update(sql, articleID, reportUserID, report)>0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
