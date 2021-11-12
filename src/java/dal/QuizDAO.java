/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Fixed: 22/10/2021
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Quizzes;
import java.util.List;

/**
 *
 * @author Admin
 */
public class QuizDAO extends MyDAO {

    public int getNewQuizRowCount(String type) {
        int no = 0;
        xSql = "SELECT COUNT(*) FROM quizzes where createdDate = curdate() and type = ? ;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, type);
            rs = ps.executeQuery();
            if (rs.next()) {
                no = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }

  

    public int getAllQuizRowCountInRange(String fromDate, String toDate, String type) {
        int no = 0;
        xSql = "SELECT COUNT(*) FROM quizzes\n"
                + "where\n"
                + "createdDate between ? and ?\n"
                + "and\n"
                + "type = ? ;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, fromDate);
            ps.setString(2, toDate);
            ps.setString(3, type);
            rs = ps.executeQuery();
            if (rs.next()) {
                no = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }

   public int getAllNumberQuizTaken(String type, String date) {
        int no = 0;
        xSql = "select count(*) from quizzes where type = ?\n"
                + "and\n"
                + "quizzes.id in (select quiz_id from history where taken = ?);";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, type);
            ps.setString(2, date);
            rs = ps.executeQuery();
            if (rs.next()) {
                no = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }

    public int getRowCount() {
        int no = 0;
        xSql = "SELECT COUNT(*) FROM quizzes";
        try {
            ps = con.prepareStatement(xSql);

            rs = ps.executeQuery();
            if (rs.next()) {
                no = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }

    public int getRowCountForSearch(String searchName, String subject, String category, String type) {
        int no = 0;

        xSql = "SELECT COUNT(*) FROM quizzes WHERE\n"
                + "title like ?\n"
                + "and\n"
                + "subject_id like ?\n"
                + "and\n"
                + "category like ?\n"
                + "and\n"
                + "type like ?\n";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + searchName + "%");
            ps.setString(2, "%" + subject + "%");
            ps.setString(3, "%" + category + "%");
            ps.setString(4, "%" + type + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                no = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }

    public ArrayList<Quizzes> listAllQuizes(int pageIndex, int pageSize, String searchName, String subject, String category, String type) {
        int numberOfRecord = (pageIndex - 1) * pageSize;

        ArrayList<Quizzes> list = new ArrayList<>();
        xSql = "SELECT * FROM quizzes\n"
                + "where\n"
                + "title like ?\n"
                + "and\n"
                + "subject_id like ?\n"
                + "and\n"
                + "category like ?\n"
                + "and\n"
                + "type like ?\n"
                + "order by id desc\n"
                + "limit ?,? ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + searchName + "%");
            ps.setString(2, "%" + subject + "%");
            ps.setString(3, "%" + category + "%");
            ps.setString(4, "%" + type + "%");
            ps.setInt(5, numberOfRecord);
            ps.setInt(6, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Quizzes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getFloat(11), rs.getString(12), rs.getDate(13), rs.getBoolean(14)));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Quizzes> getQuiz() {
        ArrayList<Quizzes> x = new ArrayList<>();
        xSql = "SELECT * FROM Quizzes";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                x.add(new Quizzes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getFloat(11), rs.getString(12),rs.getDate(13), rs.getBoolean(14)));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public ArrayList<Quizzes> getFourFeaturedQuiz() {
        ArrayList<Quizzes> list = new ArrayList<>();
        xSql = "SELECT id,title,thumnail FROM Quizzes where type = 'Free Test' and isFeatured = true order by id desc LIMIT 4";

        try {

            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
               Quizzes quiz = new Quizzes();
               quiz.setId(rs.getInt(1));
               quiz.setTitle(rs.getString(2));
               quiz.setThumbnail(rs.getString(3));
               list.add(quiz);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteQuiz(int id) {
        xSql = "delete from Quizzes where id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addQuiz(Quizzes q) {
        xSql = "INSERT INTO Quizzes\n"
                + "           (title\n"
                + "           ,description\n"
                + "           ,subject_id\n"
                + "           ,category\n"
                + "           ,level\n"
                + "           ,type\n"
                + "           ,user_id\n"
                + "           ,numberOfQuestion\n"
                + "           ,duration\n"
                + "           ,rate\n"
                + "           ,thumnail\n"
                + "           ,createdDate\n"
                + "           ,isFeatured)\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, q.getTitle());
            ps.setString(2, q.getDescription());
            ps.setInt(3, q.getSubject_id());
            ps.setString(4, q.getCategory());
            ps.setString(5, q.getLevel());
            ps.setString(6, q.getType());
            ps.setInt(7, q.getUser_id());
            ps.setInt(8, q.getNumber_of_question());
            ps.setInt(9, q.getDuration());
            ps.setFloat(10, q.getRate());
            ps.setString(11, q.getThumbnail());
            ps.setDate(12, q.getCreate_date());
            ps.setBoolean(13, q.isIsFeatured());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Quizzes getQuizByID(String id) {
        xSql = "select * from Quizzes where id = ?";
        Quizzes q = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                q = new Quizzes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getFloat(11), rs.getString(12), rs.getDate(13), rs.getBoolean(14));
            }
            return q;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void UpdateQuizzes(Quizzes q) {

        xSql = "UPDATE Quizzes\n"
                + "   SET title = ?\n"
                + "      ,description = ?\n"
                + "      ,subject_id = ?\n"
                + "      ,category = ?\n"
                + "      ,level = ?\n"
                + "      ,type = ?\n"
                + "      ,user_id = ?\n"
                + "      ,numberOfQuestion = ?\n"
                + "      ,duration = ?\n"
                + "      ,rate = ?\n"
                + "      ,thumnail = ?\n"
            
                + " WHERE id=?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, q.getTitle());
            ps.setString(2, q.getDescription());
            ps.setInt(3, q.getSubject_id());
            ps.setString(4, q.getCategory());
            ps.setString(5, q.getLevel());
            ps.setString(6, q.getType());
            ps.setInt(7, q.getUser_id());
            ps.setInt(8, q.getNumber_of_question());
            ps.setInt(9, q.getDuration());
            ps.setFloat(10, q.getRate());
            ps.setString(11, q.getThumbnail());
          
            ps.setInt(12, q.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Quizzes> getPractice(int uid) {
        ArrayList<Quizzes> x = new ArrayList<>();
        xSql = "SELECT * FROM Quizzes where type='User Practice' and user_id=?";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                x.add(new Quizzes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getFloat(11), rs.getString(12), rs.getDate(13), rs.getBoolean(14)));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public ArrayList<Quizzes> getQuizByType(String type) {
        ArrayList<Quizzes> x = new ArrayList<>();
        xSql = "SELECT * FROM Quizzes where type = ? order by id desc limit 6";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, type);
            rs = ps.executeQuery();
            while (rs.next()) {
                x.add(new Quizzes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getFloat(11), rs.getString(12), rs.getDate(13), rs.getBoolean(14)));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public ArrayList<Quizzes> getQuizByTypeCategory(String type, String category) {
        ArrayList<Quizzes> x = new ArrayList<>();
        xSql = "SELECT * FROM Quizzes where type = ? and category = ? order by id desc limit 6";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, type);
            ps.setString(2, category);
            rs = ps.executeQuery();
            while (rs.next()) {
                x.add(new Quizzes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getFloat(11), rs.getString(12), rs.getDate(13), rs.getBoolean(14)));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

//    public static void main(String[] args) {
//        QuizDAO dao = new QuizDAO();
//        dao.addQuiz(new Quizzes("abc", "abc", 1, 10, "Free Test", 25, 0, 200, 0.6f, "abc"));
//    }
    public List<Quizzes> getQuizByName(String res) {
        List<Quizzes> list = new ArrayList();
        xSql = "SELECT * FROM Quizzes where `title` like ? and `type` = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + res + "%");
            ps.setString(2, "Free Test");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Quizzes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getFloat(11), rs.getString(12), rs.getDate(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Quizzes> getQuizbyCategory(String type) {

        List<Quizzes> list = new ArrayList();
        xSql = "SELECT *\n" + "FROM Quizzes\n" + "where category like ? and type = 'Free Test' ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, type);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Quizzes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getFloat(11), rs.getString(12), rs.getDate(13), rs.getBoolean(14)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isModifyQuiz(int quiz_id) {

        xSql = "SELECT * FROM history where quiz_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, quiz_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
        public int getRowCountAllFreeTest() {
        int no = 0;
        xSql = "SELECT COUNT(*) FROM quizzes where type = 'Free Test'";
        try {
            ps = con.prepareStatement(xSql);

            rs = ps.executeQuery();
            if (rs.next()) {
                no = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }

    public int getRowCountForFilterFreeTest(String searchName, String category) {
        int no = 0;
        xSql = "SELECT COUNT(*) FROM quizzes WHERE title like ? and category like ? and type = 'Free Test'";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + searchName + "%");
            ps.setString(2, "%" + category + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                no = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }

    public ArrayList<Quizzes> listAllFreeQuizes(int pageIndex, int pageSize, String searchName, String category) {

        int numberOfRecord = (pageIndex - 1) * pageSize;

        ArrayList<Quizzes> list = new ArrayList<>();
        xSql = "select * from quizzes\n"
                + "where\n"
                + "type = 'Free Test'\n"
                + "and\n"
                + "title like ?\n"
                + "and\n"
                + "category like ?\n"
                + "order by id desc\n"
                + "limit ?,?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + searchName + "%");
            ps.setString(2, "%" + category + "%");
            ps.setInt(3, numberOfRecord);
            ps.setInt(4, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Quizzes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getFloat(11), rs.getString(12), rs.getDate(13), rs.getBoolean(14)));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void changeQuizFeature(int id, String status) {
        xSql = "Update quizzes set isFeatured = ? where id= ? ";
        try {
            ps = con.prepareStatement(xSql);

            ps.setString(1, status);

            ps.setInt(2, id);

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 
}
