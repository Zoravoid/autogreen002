import type { Metadata } from "next";
import "./globals.css";
import Navbar from "./_components/_navigation/navbar";
import Header from "./_components/_navigation/header";


export const metadata: Metadata = {
  title: "Automated Greenhouse",
  description: "A project meant for creating an automated greenhouse",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
      >
        <div className="min-h-screen">
          <Header />
          <Navbar />
          <main className="container mx-auto px-4 py-8"> {children}</main>
         

        </div>
      </body>
    </html>
  );
}
