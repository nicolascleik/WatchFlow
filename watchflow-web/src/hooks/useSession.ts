import { useEffect, useState } from "react";
import { store, type User } from "@/lib/store";

export function useSession() {
  const [user, setUser] = useState<User | null | undefined>(undefined);
  useEffect(() => {
    const sync = () => setUser(store.current());
    sync();
    window.addEventListener("wf:session", sync);
    window.addEventListener("storage", sync);
    return () => {
      window.removeEventListener("wf:session", sync);
      window.removeEventListener("storage", sync);
    };
  }, []);
  return user;
}
