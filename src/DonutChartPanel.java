import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JPanel;
import java.awt.Color;

public class DonutChartPanel extends JPanel {

    public DonutChartPanel(int available, int taken) {
        // Créer un dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Occupées", taken);
        dataset.setValue("Libres", available);
        // Créer le graphique
        JFreeChart chart = ChartFactory.createRingChart(
                "Taux de disponibilité des chambres",  // Titre
                dataset,              // Données
                true,                 // Inclure la légende
                true,
                false);

        // Personnaliser le plot
        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setSectionPaint("Occupées", new Color(128, 0, 0));
        plot.setSectionPaint("Libres", new Color(0, 128, 0));
        plot.setOutlineVisible(false); // Enlever les bordures du plot
        plot.setSectionDepth(0.30);
        

        // Définir la couleur de fond du plot (y compris le trou du donut)
        plot.setBackgroundPaint(new Color(255, 255, 255));
        chart.setBackgroundPaint(new Color(255, 255, 255));
        // Créer un panel pour le graphique
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        // Ajouter le panel au JPanel courant
        add(chartPanel);
        setBackground(new Color(245, 245, 245));
    }
}
