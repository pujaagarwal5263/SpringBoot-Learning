-- Add unique constraint to email if it doesn't exist
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint WHERE conname = 'student_email_unique'
    ) THEN
        ALTER TABLE student ADD CONSTRAINT student_email_unique UNIQUE (email);
    END IF;
END $$;
