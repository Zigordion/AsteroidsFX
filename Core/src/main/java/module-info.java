import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Core {
	requires Common;
	requires javafx.graphics;
	opens dk.sdu.mmmi.cbse.main to javafx.graphics;
	uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
	uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
	uses IPostEntityProcessingService;
	uses dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
}
