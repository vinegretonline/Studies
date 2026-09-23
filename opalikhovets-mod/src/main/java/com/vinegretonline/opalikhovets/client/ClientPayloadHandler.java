package com.vinegretonline.opalikhovets.client;

import com.vinegretonline.opalikhovets.network.PhotoFlashPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public final class ClientPayloadHandler {
    private ClientPayloadHandler() {
    }

    /** Runs on the client main thread (NeoForge default for payload handlers). */
    public static void handlePhotoFlash(PhotoFlashPayload payload, IPayloadContext context) {
        PhotoFlashOverlay.trigger();
    }
}
