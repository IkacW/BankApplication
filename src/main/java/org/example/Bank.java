package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Bank {


    public static boolean withdrawMoney(int id, float amount) {

        Conn connection = new Conn();
        String q = "SELECT balance FROM `user` WHERE id = " + id;

        ResultSet rs;
        try {
            rs = connection.statement.executeQuery(q);

            float balance;
            if(rs.next()) {
                balance = rs.getFloat("balance");

                if (balance < amount) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    float new_balance = balance - amount;
                    q = "UPDATE `user` SET `balance` = " + new_balance + " WHERE id = " + id;
                    connection.statement.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, "Your request has been successfully executed.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error occurred!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }


    public static boolean depositMoney(int id, float amount) {
        Conn connection = new Conn();
        String q = "SELECT balance FROM `user` WHERE id = " + id;

        ResultSet rs;

        try {
            rs = connection.statement.executeQuery(q);

            if(rs.next()) {
                if(amount > 0) {
                    float balance = rs.getFloat("balance");
                    float new_balance = balance + amount;
                    q = "UPDATE `user` SET `balance` = " + new_balance + " WHERE id = " + id;

                    connection.statement.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, "Your request has been successfully executed", "Success", JOptionPane.INFORMATION_MESSAGE);

                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "The deposit amount must be greater that 0$", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean transferMoney(int id, long cardNo, String name, String last_name, float amount) {
        Conn connection = new Conn();
        String q = "SELECT * FROM `user` WHERE cardNo = '" + cardNo + "'";

        ResultSet rs;

        try {
            rs = connection.statement.executeQuery(q);

            String q_name;
            String q_last_name;
            float q_balance;
            if(rs.next()) {
                q_name = rs.getString("firstName");
                q_last_name = rs.getString("lastName");
                q_balance = rs.getFloat("balance");

                if (!name.equals(q_name) || !last_name.equals(q_last_name)) {
                    JOptionPane.showMessageDialog(null, "Incorrect credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            q = "SELECT * FROM `user` WHERE id = " + id;

            rs = connection.statement.executeQuery(q);
            if(rs.next()) {
                if(amount <= rs.getFloat("balance")) {
                    long from_card = rs.getLong("cardNo");
                    float new_from_balance = rs.getFloat("balance") - amount;
                    q = "UPDATE `user` SET `balance` = " + new_from_balance + " WHERE id = " + id;

                    connection.statement.executeUpdate(q);

                    float new_to_balance = q_balance + amount;
                    q = "UPDATE `user` SET `balance` = " + new_to_balance + " WHERE cardNo = " + cardNo;

                    connection.statement.executeUpdate(q);

                    LocalDateTime date_time = LocalDateTime.now();

                    q = "INSERT INTO `transaction`(`from_card`, `to_card`, `date`, `amount`) VALUES (" +
                            from_card + ", " +
                            cardNo + ", "
                            + "'" + date_time + "', "
                            + amount + ")";

                    System.out.println(from_card);
                    System.out.println(cardNo);
                    System.out.println(date_time);
                    System.out.println(amount);

                    connection.statement.executeUpdate(q);

                    JOptionPane.showMessageDialog(null, "Your request has been successfully executed", "Success", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean showHistory(int id, int page, JPanel historyPanel) {
        ResultSet rs;
        Conn connection = new Conn();
        String q = "SELECT cardNo FROM `user` where id = " + id;

        try {
            rs = connection.statement.executeQuery(q);
            long cardNo;
            if(rs.next()) {
                cardNo = rs.getLong("cardNo");
            } else {
                JOptionPane.showMessageDialog(null, "Error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            q = "SELECT * FROM `transaction` WHERE from_card = " + cardNo + "or to_card = " + cardNo + " LIMIT 9 OFFSET " + page * 8 ;
            rs = connection.statement.executeQuery(q);

            int counter = 0;
            for(int i = 28; rs.next(); i+=18) {
                counter++;
                if(counter == 9) {
                    return true;
                }
                JLabel card_no_l = new JLabel(rs.getString("from_card"));
                card_no_l.setFont(new Font("Swansea", Font.PLAIN, 10));
                card_no_l.setBounds(6, i, 85, 12);
                card_no_l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                historyPanel.add(card_no_l);

                JLabel card_to_l = new JLabel(rs.getString("to_card"));
                card_to_l.setFont(new Font("Swansea", Font.PLAIN, 10));
                card_to_l.setBounds(97, i, 85, 12);
                card_to_l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                historyPanel.add(card_to_l);

                JLabel amount_l = new JLabel(rs.getString("amount"));
                amount_l.setFont(new Font("Swansea", Font.PLAIN, 10));
                amount_l.setBounds(188, i, 50, 12);
                amount_l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                historyPanel.add(amount_l);

                JLabel date_time_l = new JLabel(rs.getString("date"));
                date_time_l.setFont(new Font("Swansea", Font.PLAIN, 10));
                date_time_l.setBounds(244, i, 60, 12);
                date_time_l.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                historyPanel.add(date_time_l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
}
