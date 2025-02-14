import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5174, // Change this to your desired port
    proxy:{
      '/api':'http://localhost:8080',
    },
  },
});
