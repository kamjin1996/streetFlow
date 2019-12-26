/*
 * Created by JFormDesigner on Wed Dec 25 12:39:45 CST 2019
 */

package gui;

import entity.Street;
import service.SmallHeap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

/**
 * @author a
 */
public class ZhuBoUtil extends JFrame {
    public static void main(String[] args) {
        new ZhuBoUtil().setVisible(true);
    }

    private List<Street> streetList = new ArrayList<>();

    public ZhuBoUtil() {
        initComponents();
    }

    private void enterActionPerformed(ActionEvent e) {
        String newStreetFlow = this.streetFlow.getText();
        if (null != newStreetFlow && !"".equals(newStreetFlow)) {
            String[] streetFlows = newStreetFlow.split("\n");
            for (String streetFlow : streetFlows) {
                String[] s = streetFlow.split(" ");
                String jiedao = s[0];
                String flow = s[1];
                boolean flowMatche = false;
                if (flow != null) {
                    flowMatche = flow.matches("^[0-9]*$");
                }
                if (s.length != 2 || (jiedao == null || "".equals(jiedao)) || ("".equals(flow)) || !flowMatche) {
                    JOptionPane.showMessageDialog(null, "输入街道信息:[" + streetFlow + "]不正确!请纠正为：“东南西北 123”此种格式的街道信息");
                    return;
                }
                streetList.add(new Street(jiedao, Integer.valueOf(flow)));
            }

            //显示最新排名
            this.showRank(this.getStreetRank(streetList));
        }
    }

    private void showRank(List<Street> streetList) {
        String rankText = this.getRankText(streetList.iterator());
        this.nearest.setText(rankText);
        //重绘
        this.nearest.updateUI();
    }

    /**
     * 得到排行的字符串
     *
     * @param iterator
     * @return
     */
    private String getRankText(Iterator<Street> iterator) {
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            Street next = iterator.next();
            sb.append(next.getStreet());
            sb.append(" ");
            sb.append(next.getFlowNum());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 数组反转
     *
     * @param arr
     * @return
     */
    public static int[] reverse(int[] arr) {
        //遍历数组
        for (int i = 0; i < arr.length / 2; i++) {
            //交换元素 因为i从0开始所以这里一定要再减去1
            int temp = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = arr[i];
            arr[i] = temp;
        }
        //返回反转后的结果
        return arr;
    }

    /**
     * 获取街道排名
     *
     * @param streetList
     * @return
     */
    private List<Street> getStreetRank(List<Street> streetList) {
        int[] flowArr = streetList.stream().mapToInt(Street::getFlowNum).toArray();

        int[] tops = SmallHeap.topN(flowArr, Math.min(flowArr.length, 10));

        List<Street> result = new ArrayList<>();
        for (int top : tops) {
            for (Street street : streetList) {
                if (top == street.getFlowNum()) {
                    if (result.contains(street)) {
                        continue;
                    }
                    result.add(street);
                }
            }
        }
        result.sort(new Comparator<Street>() {
            @Override
            public int compare(Street o1, Street o2) {
                return o2.getFlowNum() - o1.getFlowNum();
            }
        });
        return result;
    }

    private void resetActionPerformed(ActionEvent e) {
        this.streetFlow.setText("");
        this.nearest.setText("");
        this.streetList.clear();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("mainJfram");
        reset = new JButton();
        enter = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        nearest = new JTextArea();
        scrollPane2 = new JScrollPane();
        streetFlow = new JTextArea();

        //======== this ========
        setTitle(bundle.getString("ZhuBoUtil.this.title"));
        Container contentPane = getContentPane();

        //---- reset ----
        reset.setText(bundle.getString("ZhuBoUtil.reset.text"));
        reset.addActionListener(e -> resetActionPerformed(e));

        //---- enter ----
        enter.setText(bundle.getString("ZhuBoUtil.enter.text"));
        enter.addActionListener(e -> enterActionPerformed(e));

        //---- label2 ----
        label2.setText(bundle.getString("ZhuBoUtil.label2.text"));

        //---- label3 ----
        label3.setText(bundle.getString("ZhuBoUtil.label3.text"));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(nearest);
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(streetFlow);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(enter, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(reset, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)))
                                .addGap(39, 39, 39))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                        .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(enter, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(reset, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16))
        );
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton reset;
    private JButton enter;
    private JLabel label2;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTextArea nearest;
    private JScrollPane scrollPane2;
    private JTextArea streetFlow;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
