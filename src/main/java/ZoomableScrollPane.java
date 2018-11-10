import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ZoomableScrollPane extends ScrollPane {

	private double scaleValue = 0.7;
	private double zoomIntensity = 0.02;
	private Node target;
	private Node zoomNode;

	public ZoomableScrollPane(Node target, boolean pannable) {
		super();
		this.target = target;
		this.zoomNode = new Group(target);
		setContent(outerNode(zoomNode));

		setPannable(pannable);
		setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
//		double factorX = (getContent().getBoundsInParent().getWidth() - getWidth()) / getWidth();
//		double factorY = (getContent().getBoundsInParent().getHeight() - getHeight()) / getHeight();
//		if(factorX > factorY) {
//			setFitToWidth(true); //make the content fit to the visible width of the scrollPane
//			System.out.println("fitting to width");
//		} else {
//			setFitToHeight(true); //make the content fit to the visible height of the scrollPane
//			System.out.println("fitting to height");
//		}
		setFitToWidth(true);
		setFitToHeight(true);
		updateScale();
	}

	private Node outerNode(Node node) {
		Node outerNode = centeredNode(node);
		outerNode.setOnScroll(e -> {
			if(e.isControlDown()) {
				e.consume();
				onScroll(e.getTextDeltaY(), new Point2D(e.getX(), e.getY()));
			}
		});
		return outerNode;
	}

	private Node centeredNode(Node node) {
		VBox vBox = new VBox(node);
		vBox.setAlignment(Pos.CENTER);
		return vBox;
	}

	private void updateScale() {
		target.setScaleX(scaleValue);
		target.setScaleY(scaleValue);
	}

	private void onScroll(double wheelDelta, Point2D mousePoint) {
		double zoomFactor = Math.exp(wheelDelta * zoomIntensity);

		Bounds innerBounds = zoomNode.getLayoutBounds();
		Bounds viewportBounds = getViewportBounds();

		// calculate pixel offsets from [0, 1] range
		double valX = this.getHvalue() * (innerBounds.getWidth() - viewportBounds.getWidth());
		double valY = this.getVvalue() * (innerBounds.getHeight() - viewportBounds.getHeight());

		scaleValue = scaleValue * zoomFactor;
		updateScale();
		this.layout(); // refresh ScrollPane scroll positions & target bounds

		// convert target coordinates to zoomTarget coordinates
		Point2D posInZoomTarget = target.parentToLocal(zoomNode.parentToLocal(mousePoint));

		// calculate adjustment of scroll position (pixels)
		Point2D adjustment = target.getLocalToParentTransform().deltaTransform(posInZoomTarget.multiply(zoomFactor - 1));

		// convert back to [0, 1] range
		// (too large/small values are automatically corrected by ScrollPane)
		Bounds updatedInnerBounds = zoomNode.getBoundsInLocal();
		this.setHvalue((valX + adjustment.getX()) / (updatedInnerBounds.getWidth() - viewportBounds.getWidth()));
		this.setVvalue((valY + adjustment.getY()) / (updatedInnerBounds.getHeight() - viewportBounds.getHeight()));
	}

	public void setTarget(Node target) {
		this.target = target;
		this.zoomNode = new Group(this.target);
		setContent(outerNode(zoomNode));
		updateScale();
	}

	public Node getContents() {
		return ((Group) ((VBox) getContent()).getChildren().get(0)).getChildren().get(0);
	}
}