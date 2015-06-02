package ru.ifmo.qd.testing.client.view.admin.editor;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.view.admin.wip.UpdateListener;
import ru.ifmo.qd.testing.client.model.tests.Part;

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
public class CreatePartWidget extends Composite {
    private int ownerID = -1;
    private VerticalPanel mainPanel = new VerticalPanel();
    private Label title = new HTML("<h1>Part editor:</h1>");
    private List parts = new ArrayList();
    private Button createButton = new Button("create Part");
    private DockPanel tableWrapper = new DockPanel();


    public CreatePartWidget(int id) {
        ownerID = id;
        initWidget(mainPanel);
        mainPanel.add(title);
        mainPanel.add(tableWrapper);
        createButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                CreatePartDialog partDialog = new CreatePartDialog(ownerID);
                partDialog.show();
                partDialog.center();
                partDialog.addUpdateListener(new UpdateListener() {

                    public void updated(Object object) {
                        parts.add(object);
                        rebuildPartsTable();
                    }
                }

                );

            }
        });
        mainPanel.add(createButton);
        loadParts(ownerID);
    }

    private void loadParts(int id) {
        TestService.App.getInstance().getPartsList(id, new AsyncCallback() {

            public void onFailure(Throwable throwable) {
                Window.alert("Can't get list!");
            }

            public void onSuccess(Object object) {
                if (object == null) return;
                parts = (ArrayList) object;
                rebuildPartsTable();
            }
        });
    }

    private Hyperlink addLink(Part part) {
        return new Hyperlink("edit", "edit_part=" + part.getId());

    }


    private void rebuildPartsTable() {
        tableWrapper.clear();
        FlexTable table = new FlexTable();
        int rows = -1;
        table.setHTML(++rows, 0, "ID");
        table.setHTML(rows, 1, "Part Name");
        table.setHTML(rows, 2, "Description");
        table.setHTML(rows, 3, "Edit?");
        table.setHTML(rows, 4, "Delete?");
        for (Iterator iter = parts.iterator(); iter.hasNext();) {
            Part part = (Part) iter.next();
            table.setHTML(++rows, 0, String.valueOf(part.getId()));
            table.setHTML(rows, 1, part.getPartName());
            table.setHTML(rows, 2, part.getDescription());
            table.setWidget(rows, 3, addLink(part));
            table.setWidget(rows, 4, new PartDeleteButton(part));
        }
        tableWrapper.add(table, DockPanel.CENTER);
    }


    private class PartDeleteButton extends Button {

        private Part part;

        public PartDeleteButton(Part part) {
            super("delete");
            this.part = part;
            addClickListener(clickListener());
        }

        private ClickListener clickListener() {
            return new ClickListener() {

                public void onClick(Widget sender) {
                    setEnabled(false);
                    TestService.App.getInstance().removePart(part.getId(), new AsyncCallback() {

                        public void onFailure(Throwable caught) {
                            Window.alert("Error connecting with Database");
                        }

                        public void onSuccess(Object result) {
                            parts.remove(part);
                            rebuildPartsTable();
                        }


                    });
                }
            };

        }

    }

    private class CreatePartDialog extends DialogBox implements ClickListener {

        private VerticalPanel mainPanel = new VerticalPanel();

        private Part part = null;
        private int id;

        private TextBox name = new TextBox();
        private TextBox description = new TextBox();
        private Button applyButton = new Button("OK", this);
        private HorizontalPanel tempPanel;
        private List updateListeners = new ArrayList();

        public CreatePartDialog(int id) {
            this.id = id;
            setText("New Part");


            Button closeButton = new Button("Close", this);
            tempPanel = new HorizontalPanel();
            tempPanel.add(new Label("Part Name: "));
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

        private void createPart() {

            TestService.App.getInstance().createPart(id, name.getText(), description.getText(), new AsyncCallback() {

                public void onFailure(Throwable caught) {
                    Window.alert("Not created! " + caught.getMessage());
                }

                public void onSuccess(Object result) {
                    fireUpdate((Part) result);
                }
            });

        }

        public void onClick(Widget sender) {
            if (sender == applyButton) {
                applyButton.setEnabled(false);
                createPart();
            }
            hide();
        }

        public void addUpdateListener(UpdateListener listener) {
            updateListeners.add(listener);
        }

        private void fireUpdate(Part part) {
            for (Iterator iterator = updateListeners.iterator(); iterator.hasNext();) {
                UpdateListener updateListener = (UpdateListener) iterator.next();
                updateListener.updated(part);
            }
        }


    }
}

