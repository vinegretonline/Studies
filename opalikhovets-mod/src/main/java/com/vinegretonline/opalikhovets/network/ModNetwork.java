package com.vinegretonline.opalikhovets.network;

import com.vinegretonline.opalikhovets.client.ClientPayloadHandler;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public final class ModNetwork {
    private static final String PROTOCOL_VERSION = "1";

    private ModNetwork() {
    }

    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(PROTOCOL_VERSION);
        // The lambda body is only executed on the client, so the client class is never loaded on a dedicated server.
        registrar.playToClient(PhotoFlashPayload.TYPE, PhotoFlashPayload.STREAM_CODEC,
                (payload, context) -> ClientPayloadHandler.handlePhotoFlash(payload, context));
    }
}
