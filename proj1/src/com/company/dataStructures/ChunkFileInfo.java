package com.company.dataStructures;

import java.util.ArrayList;

public class ChunkFileInfo {

    public ArrayList<Chunk> chunks = new ArrayList<>();

    public ChunkFileInfo(){
    }

    public Chunk getChunk(int chunkNo) {
        for (Chunk chunk : chunks) {
            if (chunk.getChunkNo() == chunkNo) {
                return chunk;
            }
        }
        return null;
    }

    public boolean chunkExists(int chunkNo){
        for (Chunk chunk : chunks) {
            if (chunk.getChunkNo() == chunkNo) {
                return true;
            }
        }
        return false;
    }

    public void addChunk(Chunk chunk) {
        for(Chunk c: chunks){
            if (c.getChunkNo() == chunk.getChunkNo())
                return;
        }
        chunks.add(chunk);
    }

    public boolean removeChunk(int chunkNo) {
        if (!this.chunkExists(chunkNo)) {
            return false;
        }

        for (int i = 0; i < this.chunks.size(); i++) {
            if (this.chunks.get(i).getChunkNo() == chunkNo) {
                this.chunks.remove(i);
                return true;
            }
        }

        return false;
    }
}
