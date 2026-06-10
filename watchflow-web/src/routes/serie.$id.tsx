import { createFileRoute } from "@tanstack/react-router";
import { DetailPage } from "./filme.$id";

export const Route = createFileRoute("/serie/$id")({
  component: () => <DetailPage type="tv" />,
});
