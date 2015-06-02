package ru.ifmo.qd.testing.client.view.admin.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.view.admin.wip.UpdateListener;
import ru.ifmo.qd.testing.client.model.tests.Block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 29.11.2007
 * Time: 5:09:11
 * To change this template use File | Settings | File Templates.
 */
public class CreateBlockWidget extends Composite {
    private int ownerID = -1;
    private VerticalPanel mainPanel = new VerticalPanel();
    private Label title = new HTML("<h1>Редактор блока:</h1>");
    private List blocks = new ArrayList();
    private Button createButton = new Button("создать блок");
    private DockPanel tableWrapper = new DockPanel();


    public CreateBlockWidget(int id) {
        ownerID = id;
        initWidget(mainPanel);
        mainPanel.add(title);
        mainPanel.add(tableWrapper);
        createButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                CreateBlockDialog blockDialog = new CreateBlockDialog(ownerID);
                blockDialog.show();
                blockDialog.center();
                blockDialog.addUpdateListener(new UpdateListener() {

                    public void updated(Object object) {
                        blocks.add(object);
                        rebuildBlocksTable();
                    }
                }

                );

            }
        });
        mainPanel.add(createButton);
        loadBlocks(ownerID);
    }

    private void loadBlocks(int subjectID) {
        TestService.App.getInstance().getBlockList(subjectID, new AsyncCallback<List<Block>>() {

            public void onFailure(Throwable throwable) {
                Window.alert("Can't get questions list!");
            }

            public void onSuccess(List<Block> object) {
                if (object == null) return;
                blocks = object;
                rebuildBlocksTable();
            }
        });
    }

    private Hyperlink addLink(Block block) {
        return new Hyperlink("ред.", "edit_block=" + block.getId());

    }


    private void rebuildBlocksTable() {
        tableWrapper.clear();
        FlexTable table = new FlexTable();
        int rows = -1;
        table.setHTML(++rows, 0, "ID");
        table.setHTML(rows, 1, "Название");
        table.setHTML(rows, 2, "Описание");
        table.setHTML(rows, 3, "Ред.?");
        table.setHTML(rows, 4, "Удалить?");
        for (Iterator iter = blocks.iterator(); iter.hasNext();) {
            Block block = (Block) iter.next();
            table.setHTML(++rows, 0, String.valueOf(block.getId()));
            table.setHTML(rows, 1, block.getBlockName());
            table.setHTML(rows, 2, block.getDescription());
            table.setWidget(rows, 3, addLink(block));
            table.setWidget(rows, 4, new BlockDeleteButton(block));
        }
        tableWrapper.add(table, DockPanel.CENTER);
    }


    private class BlockDeleteButton extends Button {

        private Block block;

        public BlockDeleteButton(Block block) {
            super("удалить");
            this.block = block;
            addClickListener(clickListener());
        }

        private ClickListener clickListener() {
            return new ClickListener() {

                public void onClick(Widget sender) {
                    setEnabled(false);
                    TestService.App.getInstance().removeBlock(block.getId(), new AsyncCallback() {

                        public void onFailure(Throwable caught) {
                            Window.alert("Error connecting with Database");
                        }

                        public void onSuccess(Object result) {
                            blocks.remove(block);
                            rebuildBlocksTable();
                        }


                    });
                }
            };

        }

    }

    private class CreateBlockDialog extends DialogBox implements ClickListener {

        private VerticalPanel mainPanel = new VerticalPanel();

        private Block block = null;
        private int subjectID;

        private TextBox name = new TextBox();
        private TextBox description = new TextBox();
        private Button applyButton = new Button("OK", this);
        private HorizontalPanel tempPanel;
        private List updateListeners = new ArrayList();

        public CreateBlockDialog(int subjectID) {
            this.subjectID = subjectID;
            setText("Новый блок");


            Button closeButton = new Button("Close", this);
            tempPanel = new HorizontalPanel();
            tempPanel.add(new Label("Block Name: "));
            tempPanel.add(name);
            mainPanel.add(tempPanel);
            tempPanel = new HorizontalPanel();
            tempPanel.add(new Label("Short Description: "));
            tempPanel.add(description);
            mainPanel.add(tempPanel);
            tempPanel = new HorizontalPanel();

            tempPanel = new HorizontalPanel();
            tempPanel.add(closeButton);
            tempPanel.add(applyButton);
            mainPanel.add(tempPanel);


            DockPanel dock = new DockPanel();
            dock.setSpacing(4);
            dock.add(mainPanel, DockPanel.CENTER);


            dock.setWidth("100%");
            setWidget(dock);

        }

        private void createBlock() {

            TestService.App.getInstance().createBlock(subjectID, name.getText(), description.getText(), new AsyncCallback() {

                public void onFailure(Throwable caught) {
                    Window.alert("Block not created!" + caught.getMessage());
                }

                public void onSuccess(Object result) {
                    fireUpdate((Block) result);
                }
            });

        }

        public void onClick(Widget sender) {
            if (sender == applyButton) {
                applyButton.setEnabled(false);
                createBlock();
            }
            hide();
        }

        public void addUpdateListener(UpdateListener listener) {
            updateListeners.add(listener);
        }

        private void fireUpdate(Block block) {
            for (Iterator iterator = updateListeners.iterator(); iterator.hasNext();) {
                UpdateListener updateListener = (UpdateListener) iterator.next();
                updateListener.updated(block);
            }
        }


    }
}
