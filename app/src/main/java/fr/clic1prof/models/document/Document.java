package fr.clic1prof.models.document;

import java.util.Date;

public class Document implements DocumentModel {

    private final String name, mediaType, fileId;
    private final int id, ownerId;
    private final long size;

    private final DocumentType type;
    private final Date creationDate, modificationDate;

    public Document(String name, String mediaType, String fileId, int id, int ownerId, long size, DocumentType type, Date creationDate, Date modificationDate) {
        this.name = name;
        this.mediaType = mediaType;
        this.fileId = fileId;
        this.id = id;
        this.ownerId = ownerId;
        this.size = size;
        this.type = type;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getOwnerId() {
        return this.ownerId;
    }

    @Override
    public String getFileId() {
        return this.fileId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getMediaType() {
        return this.mediaType;
    }

    @Override
    public Date getCreationDate() {
        return this.creationDate;
    }

    @Override
    public Date getModificationDate() {
        return this.modificationDate;
    }

    @Override
    public DocumentType getType() {
        return this.type;
    }

    @Override
    public long getSize() {
        return this.size;
    }
}
