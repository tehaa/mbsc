package connect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class extractorGuiPanel extends JPanel
{
  public JProgressBar extractProgress;
  private JLabel jLabel1;
  private JLabel jLabel3;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JLabel jLabel8;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JPanel jPanel3;
  private JPanel jPanel4;
  private JPanel jPanel5;
  private JLabel lblAppName;
  public JLabel lblDoneCount;
  private JLabel lblExtStat;
  private JLabel lblLastUpdate;
  private JLabel lblTotalCount;
  private JLabel lblUpdateFrequency;

  public extractorGuiPanel()
  {
    initComponents();
  }

  private void initComponents()
  {
    this.jPanel1 = new JPanel();
    this.jPanel3 = new JPanel();
    this.jLabel1 = new JLabel();
    this.lblAppName = new JLabel();
    this.jLabel3 = new JLabel();
    this.lblUpdateFrequency = new JLabel();
    this.jPanel2 = new JPanel();
    this.jLabel5 = new JLabel();
    this.lblExtStat = new JLabel();
    this.jLabel7 = new JLabel();
    this.lblLastUpdate = new JLabel();
    this.jPanel5 = new JPanel();
    this.jLabel6 = new JLabel();
    this.lblDoneCount = new JLabel();
    this.jLabel8 = new JLabel();
    this.lblTotalCount = new JLabel();
    this.jPanel4 = new JPanel();
    this.extractProgress = new JProgressBar();
    GroupLayout localGroupLayout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(localGroupLayout);
    localGroupLayout.setHorizontalGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, 32767));
    localGroupLayout.setVerticalGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, 32767));
    setBackground(Color.white);
    setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    setMaximumSize(new Dimension(600, 150));
    setMinimumSize(new Dimension(600, 150));
    setLayout(new BoxLayout(this, 3));
    this.jPanel3.setMaximumSize(new Dimension(540, 30));
    this.jPanel3.setMinimumSize(new Dimension(540, 30));
    this.jPanel3.setOpaque(false);
    this.jPanel3.setLayout(new BoxLayout(this.jPanel3, 2));
    this.jLabel1.setFont(new Font("Ubuntu", 1, 14));
    this.jLabel1.setText("Application Name");
    this.jLabel1.setMaximumSize(new Dimension(130, 20));
    this.jPanel3.add(this.jLabel1);
    this.lblAppName.setText("Application Name");
    this.lblAppName.setMaximumSize(new Dimension(155, 20));
    this.lblAppName.setMinimumSize(new Dimension(155, 20));
    this.jPanel3.add(this.lblAppName);
    this.jLabel3.setFont(new Font("Ubuntu", 1, 14));
    this.jLabel3.setHorizontalAlignment(4);
    this.jLabel3.setText("Update Frequency");
    this.jLabel3.setMaximumSize(new Dimension(180, 20));
    this.jLabel3.setMinimumSize(new Dimension(180, 20));
    this.jLabel3.setPreferredSize(new Dimension(180, 20));
    this.jPanel3.add(this.jLabel3);
    this.lblUpdateFrequency.setHorizontalAlignment(4);
    this.lblUpdateFrequency.setText("On Request");
    this.lblUpdateFrequency.setMaximumSize(new Dimension(85, 20));
    this.lblUpdateFrequency.setMinimumSize(new Dimension(85, 20));
    this.lblUpdateFrequency.setPreferredSize(new Dimension(85, 20));
    this.jPanel3.add(this.lblUpdateFrequency);
    add(this.jPanel3);
    this.jPanel2.setMaximumSize(new Dimension(540, 30));
    this.jPanel2.setMinimumSize(new Dimension(540, 30));
    this.jPanel2.setOpaque(false);
    this.jPanel2.setPreferredSize(new Dimension(540, 30));
    this.jPanel2.setLayout(new BoxLayout(this.jPanel2, 2));
    this.jLabel5.setFont(new Font("Ubuntu", 1, 14));
    this.jLabel5.setText("Extration Status");
    this.jLabel5.setMaximumSize(new Dimension(130, 20));
    this.jLabel5.setMinimumSize(new Dimension(130, 20));
    this.jLabel5.setPreferredSize(new Dimension(130, 20));
    this.jPanel2.add(this.jLabel5);
    this.lblExtStat.setText("Waiting ....");
    this.lblExtStat.setMaximumSize(new Dimension(155, 20));
    this.lblExtStat.setMinimumSize(new Dimension(155, 20));
    this.lblExtStat.setPreferredSize(new Dimension(155, 20));
    this.jPanel2.add(this.lblExtStat);
    this.jLabel7.setFont(new Font("Ubuntu", 1, 14));
    this.jLabel7.setHorizontalAlignment(4);
    this.jLabel7.setText("Last Update   ");
    this.jLabel7.setToolTipText("");
    this.jLabel7.setMaximumSize(new Dimension(180, 20));
    this.jLabel7.setMinimumSize(new Dimension(180, 20));
    this.jLabel7.setPreferredSize(new Dimension(180, 20));
    this.jPanel2.add(this.jLabel7);
    this.lblLastUpdate.setText("2016-06-02");
    this.lblLastUpdate.setMaximumSize(new Dimension(85, 20));
    this.lblLastUpdate.setMinimumSize(new Dimension(85, 20));
    this.lblLastUpdate.setPreferredSize(new Dimension(85, 20));
    this.jPanel2.add(this.lblLastUpdate);
    add(this.jPanel2);
    this.jPanel5.setMaximumSize(new Dimension(540, 30));
    this.jPanel5.setMinimumSize(new Dimension(540, 30));
    this.jPanel5.setOpaque(false);
    this.jPanel5.setPreferredSize(new Dimension(540, 30));
    this.jPanel5.setLayout(new BoxLayout(this.jPanel5, 2));
    this.jLabel6.setFont(new Font("Ubuntu", 1, 14));
    this.jLabel6.setText("Records Extracted");
    this.jLabel6.setMaximumSize(new Dimension(130, 20));
    this.jLabel6.setMinimumSize(new Dimension(130, 20));
    this.jLabel6.setPreferredSize(new Dimension(130, 20));
    this.jPanel5.add(this.jLabel6);
    this.lblDoneCount.setText("0");
    this.lblDoneCount.setMaximumSize(new Dimension(155, 20));
    this.lblDoneCount.setMinimumSize(new Dimension(155, 20));
    this.lblDoneCount.setPreferredSize(new Dimension(155, 20));
    this.jPanel5.add(this.lblDoneCount);
    this.jLabel8.setFont(new Font("Ubuntu", 1, 14));
    this.jLabel8.setHorizontalAlignment(4);
    this.jLabel8.setText("Record Count   ");
    this.jLabel8.setToolTipText("");
    this.jLabel8.setMaximumSize(new Dimension(180, 20));
    this.jLabel8.setMinimumSize(new Dimension(180, 20));
    this.jLabel8.setPreferredSize(new Dimension(180, 20));
    this.jPanel5.add(this.jLabel8);
    this.lblTotalCount.setText("999999999");
    this.lblTotalCount.setMaximumSize(new Dimension(85, 20));
    this.lblTotalCount.setMinimumSize(new Dimension(85, 20));
    this.lblTotalCount.setPreferredSize(new Dimension(85, 20));
    this.jPanel5.add(this.lblTotalCount);
    add(this.jPanel5);
    this.jPanel4.setMaximumSize(new Dimension(540, 30));
    this.jPanel4.setMinimumSize(new Dimension(540, 30));
    this.jPanel4.setOpaque(false);
    this.jPanel4.setLayout(new BorderLayout());
    this.jPanel4.add(this.extractProgress, "Center");
    add(this.jPanel4);
  }

  public void setGeneralLabels(String paramString1, String paramString2, String paramString3)
  {
    this.lblAppName.setText(paramString1);
    this.lblUpdateFrequency.setText(paramString2);
    this.lblLastUpdate.setText(paramString3);
  }

  public void setStatus(String paramString)
  {
    this.lblExtStat.setText(paramString);
  }

  public void setRecordsCount(String paramString)
  {
    this.lblTotalCount.setText(paramString);
  }

  public void setRecordsDone(String paramString)
  {
    this.lblDoneCount.setText(paramString);
  }
}

