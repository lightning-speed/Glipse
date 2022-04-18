package glipse.ide.ui;

import glipse.ide.internal.Core;
import glipse.ide.internal.FileManager;
import glipse.ide.launcher;
import glipse.ide.util.StringUtil;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class EditorPane {
    public JScrollPane Tab;
    public JTextPane pane;
    public String fileName = "";
    private String[] texts = new String[10000];
    private StyledDocument doc;
    private Style style1;
    private Style style2;
    private Thread updateThread;
    int index = 0;
    public EditorPane(String fn) {
        this.fileName = fn;
        pane = new JTextPane();
        pane.setText(FileManager.read(fileName));
        doc = pane.getStyledDocument();
        style1 = pane.addStyle("", null);
        StyleConstants.setForeground(style1, Color.blue);
        StyleConstants.setBackground(style1, Color.white);
        StyleConstants.setBold(style1,true);
        StyleConstants.setItalic(style1,true);
        style2 = pane.addStyle("", null);
        StyleConstants.setForeground(style2, Color.black);
        StyleConstants.setBackground(style2, Color.white);
        texts[0] = pane.getText();
        index++;
        pane.setFont(new Font("Consolas", Font.PLAIN, 16));
        Tab = new JScrollPane(pane);
        Tab.setBorder(null);
        pane.setBorder(null);

        pane.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                if (e.getKeyCode() == KeyEvent.VK_Z && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
                    undo();
                    undo();
                    update();
                }
                if (e.getKeyCode() == KeyEvent.VK_Y && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
                    redo();
                    undo();
                    update();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                update();
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        });
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        Tab.setColumnHeaderView(toolBar);

        JButton run_btn = new JButton("Run");
        run_btn.setIcon(new ImageIcon("icons\\icons\\1x\\run.png"));
        run_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Core.run(fileName);
            }
        });
        toolBar.add(run_btn);

        JButton compile_btn = new JButton("Compile");
        compile_btn.setIcon(new ImageIcon("icons\\icons\\1x\\compile.png"));
        compile_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Core.compile(fileName);
            }
        });
        toolBar.add(compile_btn);
        pane.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent arg0) {

            }

            @Override
            public void keyReleased(KeyEvent arg0) {

            }

            @Override
            public void keyTyped(KeyEvent arg0) {
                texts[index] = pane.getText();
                index++;

            }
        });
        JButton undo_btn = new JButton("Undo");
        undo_btn.setIcon(new ImageIcon("icons\\icons\\1x\\undo.png"));
        undo_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                undo();
                undo();
                update();
            }
        });
        toolBar.add(undo_btn);

        JButton redo_btn = new JButton("Redo");
        redo_btn.setIcon(new ImageIcon("icons\\icons\\1x\\redo.png"));
        redo_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                redo();
                redo();
                update();
            }
        });
        toolBar.add(redo_btn);

        JButton del_btn = new JButton("Delete");
        del_btn.setIcon(new ImageIcon("icons\\icons\\1x\\delete.png"));
        del_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int result = JOptionPane.showConfirmDialog(launcher.win, "Are you sure you want to delete this Class?",
                        "Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    updateThread.suspend();
                    System.out.println(fileName);
                    new File(fileName).delete();
                    if (StringUtil.exists(FileManager.read(launcher.dirPath + "/proj"), ";" + fileName))
                        FileManager.write(launcher.dirPath + "/proj",
                                FileManager.read(launcher.dirPath + "/proj").replace(";" + fileName + "\n", ""));
                    else
                        FileManager.write(launcher.dirPath + "/proj",
                                FileManager.read(launcher.dirPath + "/proj").replace(fileName + "\n", ""));
                    MainView.tabbedPane.remove(getTab());
                    if (launcher.files[0] == "" || launcher.files[0] == null) {
                        MainView.label = new JLabel("No Class to diplay, Create a new Class from the File Menu");
                        MainView.label.setHorizontalAlignment(SwingConstants.CENTER);
                        MainView.tabbedPane.addTab("Welcome", MainView.label);
                    }
                }
            }
        });
        toolBar.add(del_btn);
       updateThread =  new Thread(new Runnable() {

            @Override
            public void run() {
                for (; ; ) {
                    try {


                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    save();
                    String art = FileManager.read(fileName).replace("  ", " ");
                    for (int i = 0; i < 5; i++)
                        art = art.replace("  ", " ");
                    run_btn.setEnabled(StringUtil.exists(art, "public static void main"));
                }

            }
        });
       updateThread.start();
        update();
    }

    public JScrollPane getTab() {
        return Tab;
    }

    public void save()  { try {
        FileManager.write(fileName, pane.getText());
    }catch (Exception e){}
    }

    public void undo() {
        if (index > 0) {
            pane.setText(texts[index - 1]);
            index--;
        }
    }

    public void redo() {
        if (texts[index] != null) {
            pane.setText(texts[index]);
            index++;
        }
    }

    public void update() {

        String t = pane.getText();
        int cp = pane.getCaretPosition();
        int start = 0;
        for (int i = 0; i < t.length(); i++) {
            try {
                if (t.charAt(i) == ' ' || t.charAt(i) == '\t'||t.charAt(i ) == '.'||t.charAt(i)=='('||t.charAt(i)==')') {

                    start = i + 1;

                } else if (i == t.length() - 2 || t.charAt(i + 1) == ' ' || t.charAt(i + 1) == '\t'||t.charAt(i + 1) == '.'||t.charAt(i+1)=='('||t.charAt(i+1)==')') {
                    doc.remove(start, i + 1 - start);
                    if (t.substring(start, i + 1).matches("(\\W)*(public|private|protected|void|static|System|String|int|char|double|float|byte|long|return|short|new|true|false|class|null)")) {
                        try {
                            doc.insertString(start, t.substring(start, i + 1), style1);
                        } catch (BadLocationException e) {
                        }
                    } else {
                        try {
                            doc.insertString(start, t.substring(start, i + 1), style2);
                        } catch (BadLocationException e) {
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        pane.setCaretPosition(cp);
    }
}
