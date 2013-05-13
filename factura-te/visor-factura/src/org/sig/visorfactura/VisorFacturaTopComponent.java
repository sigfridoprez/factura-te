/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sig.visorfactura;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ActionMap;
import javax.swing.SwingUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.nodes.Children;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.RequestProcessor;
import org.sig.derbyclient.dto.FacturaDto;
import org.sig.derbyclient.dto.ReceptoresDto;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd = "-//org.sig.visorfactura//VisorFactura//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "VisorFacturaTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "org.sig.visorfactura.VisorFacturaTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_VisorFacturaAction",
preferredID = "VisorFacturaTopComponent")
@Messages({
    "CTL_VisorFacturaAction=VisorFactura",
    "CTL_VisorFacturaTopComponent=VisorFactura Window",
    "HINT_VisorFacturaTopComponent=This is a VisorFactura window"
})
public final class VisorFacturaTopComponent extends TopComponent implements ExplorerManager.Provider {

    private static VisorFacturaTopComponent instance;
    private static ExplorerManager em = new ExplorerManager();

    public VisorFacturaTopComponent() {
        initComponents();
        setName(Bundle.CTL_VisorFacturaTopComponent());
        setToolTipText(Bundle.HINT_VisorFacturaTopComponent());
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE);
        ActionMap map = this.getActionMap();
        map.put("delete", ExplorerUtils.actionDelete(em, true)); //NOI18N
        associateLookup(ExplorerUtils.createLookup(em, map));
        RequestProcessor.getDefault().post(new Runnable() {
            @Override
            public void run() {
                readReceptores();
            }
        });
    }

    private void readReceptores() {
        //JavaDBSupport.ensureStartedDB();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                @SuppressWarnings("unchecked")
                List<ReceptoresDto> resultList = new ArrayList<ReceptoresDto>();
                ReceptoresDto dto = new ReceptoresDto("Sigfrido Perex", "PECS", "811117", "9w8");
                dto.getFacturas().add(new FacturaDto(1, "", 2));
                dto.getFacturas().add(new FacturaDto(12, "", 3));
                resultList.add(dto);

                dto = new ReceptoresDto("Sigfrido Perex 44", "PECS", "814517", "9w8");
                resultList.add(dto);
                em.setRootContext(new FacturaTRootNode(resultList));
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        beanTreeView1 = new org.openide.explorer.view.BeanTreeView();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(beanTreeView1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(beanTreeView1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openide.explorer.view.BeanTreeView beanTreeView1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }

    public static void refreshNode() {
        @SuppressWarnings("unchecked")
        List<ReceptoresDto> resultList = new ArrayList<ReceptoresDto>();
        ReceptoresDto dto = new ReceptoresDto("Sigfrido Perex", "PECS", "811117", "9w8");
        dto.getFacturas().add(new FacturaDto(1, "", 2));
        dto.getFacturas().add(new FacturaDto(12, "", 3));
        resultList.add(dto);

        dto = new ReceptoresDto("Sigfrido Perex 44", "PECS", "814517", "9w8");
        dto.getFacturas().add(new FacturaDto(1, "", 2));
        dto.getFacturas().add(new FacturaDto(12, "", 3));
        resultList.add(dto);
        
        em.setRootContext(new FacturaTRootNode(resultList));
    }
}