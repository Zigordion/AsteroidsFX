import dk.sdu.mmmi.cbse.common.services.IEventListener;

module Common {
	uses IEventListener;
	uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
	uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
	uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
	uses dk.sdu.mmmi.cbse.common.services.IUIProcessingService;

	exports dk.sdu.mmmi.cbse.common.services;
	exports dk.sdu.mmmi.cbse.common.data;
	exports dk.sdu.mmmi.cbse.common.util;
}